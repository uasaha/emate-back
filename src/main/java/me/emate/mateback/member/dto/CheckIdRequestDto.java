package me.emate.mateback.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Id 중복을 검사하는 request dto입니다.
 *
 * @author 여운석
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CheckIdRequestDto {

  private String id;
}
