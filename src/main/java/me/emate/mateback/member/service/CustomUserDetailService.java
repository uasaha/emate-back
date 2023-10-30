package me.emate.mateback.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.emate.mateback.member.dto.MemberInfoResponseDto;
import me.emate.mateback.member.exception.MemberLoginException;
import me.emate.mateback.member.repository.MemberRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * UserDetailService의 커스텀 클래스입니다.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final MemberRepository memberRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberInfoResponseDto responseMemberData;

        try {
            responseMemberData = memberRepository.memberLogin(username);
        } catch (HttpClientErrorException e) {
            throw new MemberLoginException();
        }

        List<SimpleGrantedAuthority> grantedAuthorities =
                responseMemberData.getAuthorities().stream().map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        return new User(responseMemberData.getMemberNo().toString(),
                responseMemberData.getMemberPwd(),
                grantedAuthorities);
    }
}
