package me.emate.mateback.authority.dummy;

import me.emate.mateback.authority.entity.AuthorityMember;
import me.emate.mateback.member.dummy.MemberDummy;

public class AuthorityMemberDummy {
    public static AuthorityMember dummy() {
        return new AuthorityMember(1, AuthorityDummy.dummy(), MemberDummy.dummy());
    }
}
