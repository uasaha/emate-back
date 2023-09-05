package me.emate.mateback.token.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import me.emate.mateback.token.service.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import static me.emate.mateback.token.util.TokenUtils.*;

@RestController
@RequiredArgsConstructor
public class TokenController {
    private final TokenService tokenService;
    private static final String EXP_MESSAGE = "다시 로그인 하세요.";
    private static final String TOKEN_INVALID_MESSAGE = "유효하지 않은 토큰입니다.";

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

    @GetMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        String jwt = request.getHeader(AUTH_HEADER);
        tokenService.logout(jwt);

        return ResponseEntity.ok()
                .build();
    }
}
