package me.emate.mateback.authority.repository;

import me.emate.mateback.authority.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Authority repository.
 *
 * @author 여운석
 */
public interface AuthorityRepository extends JpaRepository<Authority, Integer> {

  /**
   * 권한 명으로 권한을 조회합니다.
   *
   * @param authorityName 권한 명
   * @return 권한
   */
  Authority findAuthorityByAuthorityName(String authorityName);
}
