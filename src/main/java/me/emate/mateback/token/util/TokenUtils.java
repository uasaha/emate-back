package me.emate.mateback.token.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.annotation.PostConstruct;
import java.time.Duration;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

/**
 * Token과 관련된 유틸을 모아놓은 utils 클래스 입니다.
 *
 * @author 여운석
 */
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

  @Getter
  @Setter
  @Value("${emate.jwt.secret}")
  private String secret;

  /**
   * secret을 설정합니다.
   */
  @PostConstruct
  public void init() {
    secret = Base64.getEncoder().encodeToString(secret.getBytes());
  }

  /**
   * 토큰을 생성합니다.
   *
   * @param userId         유저 ID
   * @param authorities    유저 권한
   * @param tokenType      토큰의 타입
   * @param tokenValidTime 토큰 만료 시간
   * @return 액세스 토큰
   */
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

  /**
   * 액세스 토큰을 생성합니다.
   *
   * @param memberUuid  the member uuid
   * @param authorities the authorities
   * @return 액세스 토큰
   */
  public String createAccessToken(String memberUuid,
      Collection<? extends GrantedAuthority> authorities) {
    return createToken(memberUuid, authorities, ACCESS_TOKEN, ACCESS_TOKEN_VALID_TIME);
  }

  /**
   * Create refresh token string.
   *
   * @param memberUuid  the member uuid
   * @param authorities the authorities
   * @return 액세스 토큰
   */
  public String createRefreshToken(String memberUuid,
      Collection<? extends GrantedAuthority> authorities) {
    return createToken(memberUuid, authorities, REFRESH_TOKEN, REFRESH_TOKEN_VALID_TIME);
  }

  /**
   * Parse claims claims.
   *
   * @param token the token
   * @return 클레임
   */
  public Claims parseClaims(String token) {
    return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
  }

  /**
   * Is validate token boolean.
   *
   * @param token the token
   * @return 검증된 토큰 값인지
   */
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

  /**
   * Reissued access token string.
   *
   * @param claims the claims
   * @return 액세스 토큰
   */
  public String reissuedAccessToken(Claims claims) {
    Date now = new Date();

    return Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(now)
        .setExpiration(new Date(now.getTime() + ACCESS_TOKEN_VALID_TIME))
        .signWith(SignatureAlgorithm.HS256, secret.getBytes())
        .compact();
  }

  /**
   * Decode jwt string.
   *
   * @param jwt the jwt
   * @return 디코드 된 토큰 값
   */
  public String decodeJwt(String jwt) {
    String payload = splitBearer(jwt);

    return new String(decoder.decode(payload));
  }

  /**
   * Split bearer string.
   *
   * @param jwt the jwt
   * @return 토큰의 bearer이 제거된 값
   */
  public String splitBearer(String jwt) {
    String jsonWebToken = jwt.substring(TOKEN_TYPE.length());
    return jsonWebToken.split("\\.")[1];
  }
}
