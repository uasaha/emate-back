package me.emate.mateback.member.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
 * member를 등록할 때 사용하는 request dto입니다.
 *
 * @author 여운석
 */
@Getter
@NoArgsConstructor
public class RegisterMemberRequestDto {
    @NotNull
    @Length(max = 30)
    private String memberId;
    @NotNull
    @Length(min = 8)
    private String pwd;
    @NotNull
    @Length(max = 30)
    private String email;
    @NotNull
    @Length(max = 20)
    private String nickname;
    @Length(max = 200)
    private String intro;
}
