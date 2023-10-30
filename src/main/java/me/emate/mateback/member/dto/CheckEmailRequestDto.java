package me.emate.mateback.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * email 중복을 검사하는 request dto입니다.
 *
 * @author 여운석
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CheckEmailRequestDto {
    private String email;
}
