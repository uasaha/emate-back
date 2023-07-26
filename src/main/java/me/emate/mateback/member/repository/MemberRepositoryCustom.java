package me.emate.mateback.member.repository;

import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface MemberRepositoryCustom {
    boolean idConflictCheck(String id);

    boolean isNickConflict(String nickname);
}
