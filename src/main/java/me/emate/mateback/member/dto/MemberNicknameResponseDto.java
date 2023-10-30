package me.emate.mateback.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * Member nickname의 중복을 검사하는 response dto입니다.
 *
 * @author 여운석
 */
@Getter
@AllArgsConstructor
public class MemberNicknameResponseDto {
    private String nickname;
}
