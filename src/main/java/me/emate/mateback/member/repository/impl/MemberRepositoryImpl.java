package me.emate.mateback.member.repository.impl;

import com.querydsl.core.types.Projections;
import me.emate.mateback.authority.entity.QAuthority;
import me.emate.mateback.authority.entity.QAuthorityMember;
import me.emate.mateback.member.entity.Member;
import me.emate.mateback.member.entity.QMember;
import me.emate.mateback.member.repository.MemberRepositoryCustom;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class MemberRepositoryImpl extends QuerydslRepositorySupport implements MemberRepositoryCustom {
    public MemberRepositoryImpl() {
        super(Member.class);
    }

    QMember member = QMember.member;
    QAuthority authority = QAuthority.authority;
    QAuthorityMember authorityMember = QAuthorityMember.authorityMember;

    @Override
    public boolean idConflictCheck(String id) {
        long count = from(member)
                .select(Projections.constructor(String.class,
                        member.memberId))
                .where(member.memberId.eq(id)).fetchCount();

        return count != 0;
    }

    @Override
    public boolean isNickConflict(String nickname) {
        long count = from(member)
                .select(Projections.constructor(String.class,
                        member.nickname))
                .where(member.nickname.eq(nickname)).fetchCount();

        return count != 0;
    }
}
