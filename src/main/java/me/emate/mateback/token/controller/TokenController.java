package me.emate.mateback.token.controller;

import static me.emate.mateback.token.util.TokenUtils.ACCESS_TOKEN_VALID_TIME;
import static me.emate.mateback.token.util.TokenUtils.AUTH_HEADER;
import static me.emate.mateback.token.util.TokenUtils.EXP_HEADER;
import static me.emate.mateback.token.util.TokenUtils.TOKEN_TYPE;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import me.emate.mateback.token.service.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * JWT 인증을 위한 Token controller입니다.
 *
 * @author 여운석
 */
@RestController
@RequiredArgsConstructor
public class TokenController {

  private final TokenService tokenService;
  private static final String EXP_MESSAGE = "다시 로그인 하세요.";
  private static final String TOKEN_INVALID_MESSAGE = "유효하지 않은 토큰입니다.";

  /**
   * 토큰 재발급.
   *
   * @param accessToken the access token
   * @return the response entity
   */
  @PostMapping("/reissue")
  public ResponseEntity<Void> tokenReIssued(@RequestBody String accessToken) {
    String result = tokenService.tokenReIssued(accessToken);

    if (result.equals(EXP_MESSAGE) || result.equals(TOKEN_INVALID_MESSAGE)) {
      return ResponseEntity.ok()
          .header("X-MESSAGE", result)
          .build();
    }

    return ResponseEntity.ok()
        .header(AUTH_HEADER, TOKEN_TYPE + result)
        .header(EXP_HEADER, String.valueOf(new Date().getTime() + ACCESS_TOKEN_VALID_TIME))
        .build();
  }

  /**
   * 로그아웃.
   *
   * @param request the request
   * @return the response entity
   */
  @GetMapping("/auth/logout")
  public ResponseEntity<Void> logout(HttpServletRequest request) {
    String jwt = request.getHeader(AUTH_HEADER);
    tokenService.logout(jwt);

    return ResponseEntity.ok()
        .build();
  }
}
