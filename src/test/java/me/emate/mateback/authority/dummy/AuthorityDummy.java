package me.emate.mateback.authority.dummy;

import me.emate.mateback.authority.entity.Authority;

public class AuthorityDummy {
    public static Authority dummy() {
        return new Authority(1, "MEMBER");
    }
}
