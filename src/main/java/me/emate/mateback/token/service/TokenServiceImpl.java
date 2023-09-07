package me.emate.mateback.token.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.emate.mateback.token.dto.TokenInfoDto;
import me.emate.mateback.token.dto.TokenPayload;
import me.emate.mateback.token.exception.TokenParseException;
import me.emate.mateback.token.exception.TokenUnusualApproachException;
import me.emate.mateback.token.util.TokenUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static me.emate.mateback.member.controller.MemberController.AUTH_MEMBER_INFO;
import static me.emate.mateback.member.controller.MemberController.TOKEN_CUT_NUM;
import static me.emate.mateback.token.util.TokenUtils.REFRESH_TOKEN;
import static me.emate.mateback.token.util.TokenUtils.TOKEN_TYPE;

@Service
@Slf4j
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
    private static final String EXP_MESSAGE = "다시 로그인 하세요.";
    private static final String TOKEN_INVALID_MESSAGE = "유효하지 않은 토큰입니다.";
    private static final String BLACK_LIST = "black_list";
    private final TokenUtils tokenUtils;
    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public String tokenIssued(Integer userNo,
                              Collection<? extends GrantedAuthority> authorities) {
        String memberUuid = UUID.randomUUID().toString();

        String refreshToken = tokenUtils.createRefreshToken(memberUuid, authorities);
        String accessToken = tokenUtils.createAccessToken(memberUuid, authorities);

        redisTemplate.opsForHash().put(REFRESH_TOKEN, accessToken, refreshToken);
        redisTemplate.opsForValue().set(memberUuid, String.valueOf(userNo));

        return accessToken;
    }

    @Override
    public String tokenReIssued(String accessToken) {
        if (!tokenUtils.isValidateToken(accessToken)) {
            return TOKEN_INVALID_MESSAGE;
        }

        Claims claims = tokenUtils.parseClaims(accessToken);

        String refreshToken = getRefreshToken(accessToken);

        String payload = tokenUtils.decodeJwt(refreshToken);
        TokenInfoDto tokenInfo = getTokenInfoDto(payload);

        long validTime = tokenInfo.getExp() - (new Date().getTime() / 1000);

        String message = refreshTokenExpCheck(validTime);
        if (Objects.nonNull(message)) {
            return message;
        }
        String renewAccessToken = tokenUtils.reissuedAccessToken(claims);

        redisTemplate.opsForHash().put(REFRESH_TOKEN, renewAccessToken, refreshToken);

        return renewAccessToken;
    }

    @Override
    public void logout(String jwt) {
        String accessToken = jwt.substring(TOKEN_TYPE.length());
        Claims claims = tokenUtils.parseClaims(accessToken);
        long exp = claims.getExpiration().getTime();

        redisTemplate.opsForHash().put(BLACK_LIST, accessToken, "");
        redisTemplate.expire(accessToken, exp, TimeUnit.MICROSECONDS);
        redisTemplate.opsForHash().delete(REFRESH_TOKEN, accessToken);
    }

    private String getRefreshToken(String accessToken) {
        String refreshToken =
                (String) redisTemplate.opsForHash().get(REFRESH_TOKEN, accessToken);
        if (Objects.isNull(refreshToken)) {
            throw new TokenUnusualApproachException();
        }

        redisTemplate.opsForHash().delete(REFRESH_TOKEN, accessToken);
        return refreshToken;
    }

    private static String refreshTokenExpCheck(long validTime) {
        if (validTime <= 0) {
            return EXP_MESSAGE;
        }
        return null;
    }

    private TokenInfoDto getTokenInfoDto(String payload) {
        TokenInfoDto tokenInfo;
        try {
            tokenInfo = objectMapper.readValue(payload, TokenInfoDto.class);
        } catch (JsonProcessingException e) {
            throw new TokenParseException();
        }
        return tokenInfo;
    }


    public Claims getPayLoadValue(String accessToken) {
        Claims result = null;

        try {
            result = Jwts.parser()
                    .setSigningKey(Base64.getEncoder().encodeToString(
                            tokenUtils.getSecret().getBytes()))
                    .parseClaimsJws(accessToken)
                    .getBody();

        } catch (SignatureException |
                 MalformedJwtException e) {
            log.error("잘못된 JWT 서명입니다.");
        } catch (
                ExpiredJwtException e) {
            log.error("만료된 JWT 토큰입니다.");
        } catch (
                UnsupportedJwtException e) {
            log.error("지원되지 않는 JWT 토큰입니다.");
        } catch (
                IllegalArgumentException e) {
            log.error("JWT 토큰이 잘못되었습니다.");
        }
        return result;
    }

    @Override
    public TokenPayload getPayLoad(String accessToken) {
        String memberUUID = getPayLoadValue(accessToken)
                .get("memberUUID", String.class);

        String roles = getPayLoadValue(accessToken)
                .get("roles", String.class);

        return new TokenPayload(memberUUID, roles);
    }

    @Override
    public Integer getMemberNo(HttpServletRequest request) {
        String accessToken = request.getHeader(AUTH_MEMBER_INFO).substring(TOKEN_CUT_NUM);
        String memberUUID = getPayLoadValue(accessToken)
                .get("memberUUID", String.class);

        return Integer.parseInt(Objects.requireNonNull(redisTemplate.opsForValue()
                .get(memberUUID)));
    }
}
