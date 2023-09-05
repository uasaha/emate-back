package me.emate.mateback.member.service;

import me.emate.mateback.member.dto.*;

public interface MemberService {

    MemberNicknameResponseDto memberLogin(LoginMemberRequestDto requestDto);

    boolean isIdConflict(CheckIDRequestDto requestDto);

    boolean isNickConflict(CheckNicknameRequestDto requestDto);

    boolean isEmailConflict(CheckEmailRequestDto requestDto);

    void signup(RegisterMemberRequestDto requestDto);

    MemberDetailResponseDto getMemberDetails(Integer memberNo);
}
