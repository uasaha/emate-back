package me.emate.mateback.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Nickname 중복을 체크하는 request dto입니다.
 *
 * @author 여운석
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CheckNicknameRequestDto {

  private String nickname;
}
