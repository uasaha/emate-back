package me.emate.mateback.member.repository;

import me.emate.mateback.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer>, MemberRepositoryCustom {
    boolean existsMemberByMemberId(String memberId);
    boolean existsMemberByNickname(String nickname);
    boolean existsMemberByEmail(String email);
    Optional<Member> findMemberByMemberId(String memberId);
}
