package me.emate.mateback.authority.repository;

import me.emate.mateback.authority.entity.AuthorityMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityMemberRepository extends JpaRepository<AuthorityMember, Integer> {

}
