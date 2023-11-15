package me.emate.mateback.token.service;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Collection;
import me.emate.mateback.token.dto.TokenPayload;
import org.springframework.security.core.GrantedAuthority;

/**
 * Token service입니다.
 *
 * @author 여운석
 */
public interface TokenService {

  /**
   * 토큰을 발행합니다.
   *
   * @param userNo      the user no
   * @param authorities the authorities
   * @return the string
   */
  String tokenIssued(Integer userNo,
      Collection<? extends GrantedAuthority> authorities);

  /**
   * 토큰을 만료 시간을 확인하여 재발급 합니다.
   *
   * @param accessToken the access token
   * @return the string
   */
  String tokenReIssued(String accessToken);

  /**
   * 로그아웃.
   *
   * @param jwt the jwt
   */
  void logout(String jwt);

  /**
   * 페이로드 값을 읽어냅니다.
   *
   * @param accessToken the access token
   * @return the pay load
   */
  TokenPayload getPayLoad(String accessToken);

  /**
   * 멤버의 번호를 확인합니다.
   *
   * @param request the request
   * @return the member no
   */
  Integer getMemberNo(HttpServletRequest request);
}
