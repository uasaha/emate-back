package me.emate.mateback.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Member 정보를 교환하는 dto입니다.
 *
 * @author 여운석
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberInfoResponseDto {
    private Integer memberNo;
    private String memberId;
    private String memberPwd;
    private List<String> authorities;

    /**
     * Instantiates a new Member info response dto.
     *
     * @param memberNo  the member no
     * @param memberId  the member id
     * @param memberPwd the member pwd
     */
    public MemberInfoResponseDto(Integer memberNo, String memberId, String memberPwd) {
        this.memberNo = memberNo;
        this.memberId = memberId;
        this.memberPwd = memberPwd;
    }
}