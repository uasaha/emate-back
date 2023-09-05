package me.emate.mateback.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.emate.mateback.member.entity.Member;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberDetailResponseDto {
    private Integer memberNo;
    private String nickname;
    private String email;
    private String intro;
    private List<String> authorities;

    public MemberDetailResponseDto(Member member) {
        this.memberNo = member.getMemberNo();
        this.nickname = member.getNickname();
        this.email = member.getEmail();
        this.intro = member.getIntro();
        this.authorities = member.getAuthorities().stream()
                .map(m -> m.getAuthority().getAuthorityName())
                .collect(Collectors.toList());
    }
}
