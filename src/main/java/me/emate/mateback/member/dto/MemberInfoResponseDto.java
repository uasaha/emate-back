package me.emate.mateback.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberInfoResponseDto {
    private Integer memberNo;
    private String memberId;
    private String memberPwd;
    private List<String> authorities;

    public MemberInfoResponseDto(Integer memberNo, String memberId, String memberPwd) {
        this.memberNo = memberNo;
        this.memberId = memberId;
        this.memberPwd = memberPwd;
    }
}