package me.emate.mateback.member.dummy;

import java.util.Set;
import me.emate.mateback.authority.entity.AuthorityMember;
import me.emate.mateback.member.entity.Member;

public class MemberDummy {
    public static Member dummy() {
        return new Member(1,
                "dummyNick",
                "dummy@dummy.com",
                "dummy",
                "dummy12345",
                false,
                false,
                "imDummy",
                null);
    }

    public static Member dummy(AuthorityMember authorityMember) {
        return new Member(1,
            "dummyNick",
            "dummy@dummy.com",
            "dummy",
            "dummy12345",
            false,
            false,
            "imDummy",
            Set.of(authorityMember));
    }
}
