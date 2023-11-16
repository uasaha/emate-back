package me.emate.mateback.member.service;

import jakarta.validation.Valid;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.emate.mateback.authority.entity.Authority;
import me.emate.mateback.authority.entity.AuthorityMember;
import me.emate.mateback.authority.repository.AuthorityMemberRepository;
import me.emate.mateback.authority.repository.AuthorityRepository;
import me.emate.mateback.member.dto.CheckEmailRequestDto;
import me.emate.mateback.member.dto.CheckIdRequestDto;
import me.emate.mateback.member.dto.CheckNicknameRequestDto;
import me.emate.mateback.member.dto.LoginMemberRequestDto;
import me.emate.mateback.member.dto.MemberDetailResponseDto;
import me.emate.mateback.member.dto.MemberNicknameResponseDto;
import me.emate.mateback.member.dto.RegisterMemberRequestDto;
import me.emate.mateback.member.entity.Member;
import me.emate.mateback.member.exception.MemberLoginException;
import me.emate.mateback.member.exception.MemberNotFoundException;
import me.emate.mateback.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Member service의 구현체입니다.
 *
 * @author 여운석
 */
@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

  private final MemberRepository memberRepository;
  private final AuthorityRepository authorityRepository;
  private final AuthorityMemberRepository authorityMemberRepository;

  /**
   * {@inheritDoc}
   */
  @Override
  public MemberNicknameResponseDto memberLogin(LoginMemberRequestDto requestDto) {
    Optional<Member> member = memberRepository.findMemberByMemberId(requestDto.getId());

    if (member.isEmpty()) {
      throw new MemberLoginException();
    }

    if (member.get().getPwd().equals(requestDto.getPwd())) {
      return new MemberNicknameResponseDto(member.get().getNickname());
    }

    throw new MemberLoginException();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isIdConflict(CheckIdRequestDto requestDto) {
    return memberRepository.existsMemberByMemberId(requestDto.getId());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isNickConflict(CheckNicknameRequestDto requestDto) {
    return memberRepository.existsMemberByNickname(requestDto.getNickname());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isEmailConflict(CheckEmailRequestDto requestDto) {
    return memberRepository.existsMemberByEmail(requestDto.getEmail());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @Transactional
  public void signup(@Valid RegisterMemberRequestDto requestDto) {
    Member member = Member.builder()
        .id(requestDto.getMemberId())
        .pwd(requestDto.getPwd())
        .email(requestDto.getEmail())
        .intro(requestDto.getIntro())
        .nickname(requestDto.getNickname())
        .build();

    member = memberRepository.save(member);
    Authority authority = authorityRepository.findAuthorityByAuthorityName("MEMBER");
    AuthorityMember authorityMember = new AuthorityMember(null, authority, member);
    authorityMemberRepository.save(authorityMember);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public MemberDetailResponseDto getMemberDetails(Integer memberNo) {

    return memberRepository.getMemberDetails(memberNo)
        .orElseThrow(MemberNotFoundException::new);
  }
}
