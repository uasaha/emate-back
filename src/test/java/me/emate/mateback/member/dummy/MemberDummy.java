package me.emate.mateback.member.dummy;

import me.emate.mateback.member.entity.Member;

public class MemberDummy {
    public static Member dummy() {
        return new Member(1,
                "dummyNick",
                "dummy@dummy.com",
                "dummy",
                "dummy",
                false,
                false,
                "imDummy",
                null);
    }
}
