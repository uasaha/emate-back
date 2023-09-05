package me.emate.mateback.token.service;

import jakarta.servlet.http.HttpServletRequest;
import me.emate.mateback.token.dto.TokenPayload;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public interface TokenService {
    String tokenIssued(Integer userNo,
                       Collection<? extends GrantedAuthority> authorities);

    String tokenReIssued(String accessToken);

    void logout(String jwt);

    TokenPayload getPayLoad(String accessToken);

    Integer getMemberNo(HttpServletRequest request);
}
