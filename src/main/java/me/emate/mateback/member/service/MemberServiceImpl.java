package me.emate.mateback.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.emate.mateback.authority.entity.Authority;
import me.emate.mateback.authority.entity.AuthorityMember;
import me.emate.mateback.authority.repository.AuthorityMemberRepository;
import me.emate.mateback.authority.repository.AuthorityRepository;
import me.emate.mateback.member.dto.*;
import me.emate.mateback.member.entity.Member;
import me.emate.mateback.member.exception.MemberLoginException;
import me.emate.mateback.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final AuthorityRepository authorityRepository;
    private final AuthorityMemberRepository authorityMemberRepository;
    @Override
    @Transactional
    public MemberNicknameResponseDto memberRegister(RegisterMemberRequestDto requestDto) {
        Member member = Member.builder()
                .id(requestDto.getId())
                .pwd(requestDto.getPwd())
                .email(requestDto.getEmail())
                .intro(requestDto.getIntro())
                .nickname(requestDto.getNickname())
                .build();

        member = memberRepository.save(member);
        Authority authority = authorityRepository.findAuthorityByAuthorityName("MEMBER");
        AuthorityMember authorityMember = new AuthorityMember(null, authority, member);
        authorityMemberRepository.save(authorityMember);

        return new MemberNicknameResponseDto(member.getNickname());
    }

    @Override
    public MemberNicknameResponseDto memberLogin(LoginMemberRequestDto requestDto) {
        Optional<Member> member = memberRepository.findMemberByMemberId(requestDto.getId());

        if(member.isEmpty()) {
            throw new MemberLoginException();
        }

        if(member.get().getPwd().equals(requestDto.getPwd())) {
            return new MemberNicknameResponseDto(member.get().getNickname());
        }

        throw new MemberLoginException();
    }

    @Override
    public boolean isIdConflict(CheckIDRequestDto requestDto) {
        return memberRepository.idConflictCheck(requestDto.getId());
    }

    @Override
    public boolean isNickConflict(CheckNicknameRequestDto requestDto) {
        return memberRepository.isNickConflict(requestDto.getNickname());
    }
}
