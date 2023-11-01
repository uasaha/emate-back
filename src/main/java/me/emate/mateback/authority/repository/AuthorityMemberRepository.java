package me.emate.mateback.authority.repository;

import me.emate.mateback.authority.entity.AuthorityMember;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA를 사용하기 위한 Authority member repository입니다.
 *
 * @author 여운석
 */
public interface AuthorityMemberRepository extends JpaRepository<AuthorityMember, Integer> {

}
