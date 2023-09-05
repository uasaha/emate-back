package me.emate.mateback.member.repository.impl;

import com.querydsl.core.types.Projections;
import me.emate.mateback.authority.entity.QAuthority;
import me.emate.mateback.authority.entity.QAuthorityMember;
import me.emate.mateback.member.dto.MemberDetailResponseDto;
import me.emate.mateback.member.dto.MemberInfoResponseDto;
import me.emate.mateback.member.entity.Member;
import me.emate.mateback.member.entity.QMember;
import me.emate.mateback.member.repository.MemberRepositoryCustom;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.Optional;

public class MemberRepositoryImpl extends QuerydslRepositorySupport implements MemberRepositoryCustom {
    public MemberRepositoryImpl() {
        super(Member.class);
    }

    QMember member = QMember.member;
    QAuthority authority = QAuthority.authority;
    QAuthorityMember authorityMember = QAuthorityMember.authorityMember;

    @Override
    public MemberInfoResponseDto memberLogin(String userId) {
        MemberInfoResponseDto responseDto = from(member)
                .select(Projections.constructor(
                        MemberInfoResponseDto.class,
                        member.memberNo,
                        member.memberId,
                        member.pwd))
                .where(member.memberId.eq(userId))
                .fetchFirst();

        List<String> memberAuthorities = from(authorityMember)
                .innerJoin(authorityMember.member, member)
                .select(authorityMember.authority.authorityName)
                .where(member.memberId.eq(userId))
                .fetch();

        return new MemberInfoResponseDto(responseDto.getMemberNo(),
                responseDto.getMemberId(),
                responseDto.getMemberPwd(),
                memberAuthorities);
    }

    @Override
    public Optional<MemberDetailResponseDto> getMemberDetails(Integer memberNo) {
        Optional<Member> content = Optional.ofNullable(
                from(member)
                        .leftJoin(authorityMember)
                        .on(member.memberNo.eq(authorityMember.member.memberNo))
                        .innerJoin(authorityMember.authority, authority)
                        .where(member.memberNo.eq(memberNo))
                        .select(member)
                        .fetchOne());

        return content.map(MemberDetailResponseDto::new);
    }
}
