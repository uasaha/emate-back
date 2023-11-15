package me.emate.mateback.member.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.emate.mateback.member.entity.Member;

/**
 * Member 상세 정보를 조회하는 dto입니다.
 *
 * @author 여운석
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberDetailResponseDto {

  private Integer memberNo;
  private String nickname;
  private String email;
  private String intro;
  private List<String> authorities;

  /**
   * Instantiates a new Member detail response dto.
   *
   * @param member the member
   */
  public MemberDetailResponseDto(Member member) {
    this.memberNo = member.getMemberNo();
    this.nickname = member.getNickname();
    this.email = member.getEmail();
    this.intro = member.getIntro();
    this.authorities = member.getAuthorities().stream()
        .map(m -> m.getAuthority().getAuthorityName())
        .toList();
  }
}
