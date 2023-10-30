package me.emate.mateback.authority.repository;

import me.emate.mateback.authority.entity.AuthorityMember;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Authority member repository.
 *
 * @author 여운석
 */
public interface AuthorityMemberRepository extends JpaRepository<AuthorityMember, Integer> {

}
