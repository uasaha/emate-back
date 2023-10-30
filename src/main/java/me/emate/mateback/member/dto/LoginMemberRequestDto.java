package me.emate.mateback.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * member가 로그인 시 사용되는 request dto입니다.
 *
 * @author 여운석
 */
@Getter
@NoArgsConstructor
public class LoginMemberRequestDto {
    private String id;
    private String pwd;
}
