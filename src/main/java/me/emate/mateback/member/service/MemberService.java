package me.emate.mateback.member.service;

import me.emate.mateback.member.dto.*;

public interface MemberService {
    MemberNicknameResponseDto memberRegister(RegisterMemberRequestDto requestDto);

    MemberNicknameResponseDto memberLogin(LoginMemberRequestDto requestDto);

    boolean isIdConflict(CheckIDRequestDto requestDto);

    boolean isNickConflict(CheckNicknameRequestDto requestDto);
}
