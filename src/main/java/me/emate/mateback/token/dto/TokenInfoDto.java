package me.emate.mateback.token.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Token info dto입니다.
 *
 * @author 여운석
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TokenInfoDto {
    private String sub;
    private String memberUuid;
    private String roles;
    private String iat;
    private Long exp;
}
