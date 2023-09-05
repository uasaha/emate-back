package me.emate.mateback.member.repository;

import me.emate.mateback.member.dto.MemberDetailResponseDto;
import me.emate.mateback.member.dto.MemberInfoResponseDto;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface MemberRepositoryCustom {

    MemberInfoResponseDto memberLogin(String userId);

    Optional<MemberDetailResponseDto> getMemberDetails(Integer memberNo);
}
