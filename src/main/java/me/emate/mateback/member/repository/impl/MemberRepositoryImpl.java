package me.emate.mateback.member.repository.impl;

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

}
