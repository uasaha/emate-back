package me.emate.mateback.token.util;

import io.jsonwebtoken.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.emate.mateback.token.dto.TokenPayload;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;

@Component
@Slf4j
@RequiredArgsConstructor
public class TokenUtils {
    public static final Long ACCESS_TOKEN_VALID_TIME = Duration.ofHours(3).toMillis();
    private static final Long REFRESH_TOKEN_VALID_TIME = Duration.ofDays(1).toMillis();
    public static final String ACCESS_TOKEN = "access-token";
    public static final String REFRESH_TOKEN = "refresh-token";
    public static final String AUTH_HEADER = "Authorization";
    public static final String EXP_HEADER = "X-Expire";
    public static final String TOKEN_TYPE = "Bearer ";
    private final Base64.Decoder decoder = Base64.getUrlDecoder();

    @Value("${emate.jwt.secret}")
    private String secret;

    @PostConstruct
    public void init() {
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    private String createToken(String userId,
                               Collection<? extends GrantedAuthority> authorities,
                               String tokenType,
                               Long tokenValidTime) {
        Claims claims = Jwts.claims().setSubject(tokenType);
        claims.put("memberUUID", userId);
        claims.put("roles", authorities.toString());
        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidTime))
                .signWith(SignatureAlgorithm.HS256,
                        Base64.getEncoder().encodeToString(secret.getBytes()))
                .compact();
    }

    public String createAccessToken(String memberUuid,
                                    Collection<? extends GrantedAuthority> authorities) {
        return createToken(memberUuid, authorities, ACCESS_TOKEN, ACCESS_TOKEN_VALID_TIME);
    }

    public String createRefreshToken(String memberUuid,
                                     Collection<? extends GrantedAuthority> authorities) {
        return createToken(memberUuid, authorities, REFRESH_TOKEN, REFRESH_TOKEN_VALID_TIME);
    }

    public Claims parseClaims(String token) {
        return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
    }

    public boolean isValidateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token);
            return true;
        } catch (SignatureException | MalformedJwtException e) {
            log.error("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            log.error("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.error("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            log.error("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }

    public String reissuedAccessToken(Claims claims) {
        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + ACCESS_TOKEN_VALID_TIME))
                .signWith(SignatureAlgorithm.HS256, secret.getBytes())
                .compact();
    }

    public String decodeJwt(String jwt) {
        String payload = splitBearer(jwt);

        return new String(decoder.decode(payload));
    }

    public String splitBearer(String jwt) {
        String jsonWebToken = jwt.substring(TOKEN_TYPE.length());
        return jsonWebToken.split("\\.")[1];
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
