package me.emate.mateback.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import me.emate.mateback.member.service.CustomUserDetailService;
import me.emate.mateback.token.filter.CustomAuthenticationFilter;
import me.emate.mateback.token.provider.CustomAuthenticationProvider;
import me.emate.mateback.token.service.TokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Security를 커스텀 하기 위한 Security config입니다.
 *
 * @author 여운석
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private static final String LOGIN_REQUEST_MATCHER = "/auth/login";
  private final CustomUserDetailService userDetailsService;
  private final ObjectMapper objectMapper;

  /**
   * Password Encoder를 설정.
   *
   * @return BcryptPasswordEncoder
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  /**
   * 필터체인 설정.
   *
   * @param http the http
   * @return the security filter chain
   * @throws Exception the exception
   */
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http.csrf(AbstractHttpConfigurer::disable)
        .cors(AbstractHttpConfigurer::disable)
        .formLogin(AbstractHttpConfigurer::disable)
        .logout(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(registry -> registry.anyRequest().permitAll())
        .sessionManagement(creationPolicy ->
            creationPolicy.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .addFilterAt(customAuthenticationFilter(null),
            UsernamePasswordAuthenticationFilter.class)
        .build();
  }

  /**
   * authentication manager 빈 등록.
   *
   * @param configuration the configuration
   * @return the authentication manager
   * @throws Exception the exception
   */
  @Bean
  public AuthenticationManager getAuthenticationManager(
      AuthenticationConfiguration configuration) throws Exception {
    return configuration.getAuthenticationManager();
  }

  /**
   * Custom authentication filter 빈 등록.
   *
   * @param tokenService the token service
   * @return the custom authentication filter
   * @throws Exception the exception
   */
  @Bean
  public CustomAuthenticationFilter customAuthenticationFilter(
      TokenService tokenService) throws Exception {
    CustomAuthenticationFilter customAuthenticationFilter =
        new CustomAuthenticationFilter(customAuthenticationProvider(),
            objectMapper,
            tokenService);

    customAuthenticationFilter.setFilterProcessesUrl(LOGIN_REQUEST_MATCHER);
    customAuthenticationFilter.setAuthenticationManager(getAuthenticationManager(null));

    return customAuthenticationFilter;
  }

  /**
   * Custom authentication provider 빈 등록.
   *
   * @return the custom authentication provider
   */
  @Bean
  public CustomAuthenticationProvider customAuthenticationProvider() {
    CustomAuthenticationProvider provider
        = new CustomAuthenticationProvider();

    provider.setPasswordEncoder(passwordEncoder());
    provider.setUserDetailsService(userDetailsService);

    return provider;
  }
}
