package me.emate.mateback.member.service;

import me.emate.mateback.member.dto.LoginMemberRequestDto;
import me.emate.mateback.member.dto.RegisterMemberRequestDto;
import me.emate.mateback.member.dto.MemberNicknameResponseDto;

public interface MemberService {
    MemberNicknameResponseDto memberRegister(RegisterMemberRequestDto requestDto);

    MemberNicknameResponseDto memberLogin(LoginMemberRequestDto requestDto);
}
