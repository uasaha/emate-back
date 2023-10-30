package me.emate.mateback.member.repository;

import me.emate.mateback.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * JPA를 사용하기 위한 Member repository입니다.
 *
 * @author 여운석
 */
public interface MemberRepository extends JpaRepository<Member, Integer>, MemberRepositoryCustom {
    /**
     * Exists member by member id boolean.
     *
     * @param memberId the member id
     * @return the boolean
     */
    boolean existsMemberByMemberId(String memberId);

    /**
     * Exists member by nickname boolean.
     *
     * @param nickname the nickname
     * @return the boolean
     */
    boolean existsMemberByNickname(String nickname);

    /**
     * Exists member by email boolean.
     *
     * @param email the email
     * @return the boolean
     */
    boolean existsMemberByEmail(String email);

    /**
     * Find member by member id optional.
     *
     * @param memberId the member id
     * @return the optional
     */
    Optional<Member> findMemberByMemberId(String memberId);
}
