package me.emate.mateback.authority.dummy;

import me.emate.mateback.authority.entity.Authority;
import me.emate.mateback.authority.entity.AuthorityMember;
import me.emate.mateback.member.entity.Member;

public class AuthorityMemberDummy {

  public static AuthorityMember dummy(Authority authority, Member member) {
    return new AuthorityMember(1, authority, member);
  }
}
