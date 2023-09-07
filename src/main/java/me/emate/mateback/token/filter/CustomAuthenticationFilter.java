package me.emate.mateback.token.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.emate.mateback.member.dto.LoginMemberRequestDto;
import me.emate.mateback.member.exception.MemberParseException;
import me.emate.mateback.token.provider.CustomAuthenticationProvider;
import me.emate.mateback.token.service.TokenService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Date;

import static me.emate.mateback.token.util.TokenUtils.*;

@Slf4j
@RequiredArgsConstructor
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final CustomAuthenticationProvider provider;
    private final ObjectMapper objectMapper;
    private final TokenService tokenService;


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response)
            throws AuthenticationException {
        LoginMemberRequestDto requestDto;

        try {
            requestDto =
                    objectMapper.readValue(request.getInputStream(), LoginMemberRequestDto.class);
        } catch (IOException e) {
            throw new MemberParseException();
        }

        UsernamePasswordAuthenticationToken token
                = new UsernamePasswordAuthenticationToken(requestDto.getId(), requestDto.getPwd());

        return provider.authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) {
        String accessToken = tokenService.tokenIssued(
                Integer.parseInt((String) authResult.getPrincipal()), authResult.getAuthorities());

        response.setHeader(AUTH_HEADER, TOKEN_TYPE + accessToken);
        response.setHeader(EXP_HEADER, String.valueOf(
                new Date().getTime() + ACCESS_TOKEN_VALID_TIME));
    }
}