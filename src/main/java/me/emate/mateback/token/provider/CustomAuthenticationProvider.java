package me.emate.mateback.token.provider;

import me.emate.mateback.member.exception.MemberLoginException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

/**
 * JWT 기반 인증을 위한 Authentication provider의 Custom입니다.
 *
 * @author 여운석
 */
public class CustomAuthenticationProvider extends DaoAuthenticationProvider {

  @Override
  public Authentication authenticate(Authentication authentication) {
    String userId = (String) authentication.getPrincipal();
    String userPwd = (String) authentication.getCredentials();

    User user
        = (User) this.getUserDetailsService().loadUserByUsername(userId);

    boolean matches = this.getPasswordEncoder().matches(userPwd, user.getPassword());

    if (!matches) {
      throw new MemberLoginException();
    }

    return new UsernamePasswordAuthenticationToken(
        user.getUsername(),
        user.getPassword(),
        user.getAuthorities());
  }
}
