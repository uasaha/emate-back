package me.emate.mateback.member.repository;

import java.util.Optional;
import me.emate.mateback.member.dto.MemberDetailResponseDto;
import me.emate.mateback.member.dto.MemberInfoResponseDto;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * QueryDsl 사용을 위한 Member repository custom입니다.
 *
 * @author 여운석
 */
@NoRepositoryBean
public interface MemberRepositoryCustom {

  /**
   * Member login member info response dto.
   *
   * @param userId the user id
   * @return the member info response dto
   */
  MemberInfoResponseDto memberLogin(String userId);

  /**
   * Gets member details.
   *
   * @param memberNo the member no
   * @return the member details
   */
  Optional<MemberDetailResponseDto> getMemberDetails(Integer memberNo);
}
