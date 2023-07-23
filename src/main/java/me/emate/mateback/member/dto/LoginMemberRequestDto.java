package me.emate.mateback.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginMemberRequestDto {
    private String id;
    private String pwd;
}
