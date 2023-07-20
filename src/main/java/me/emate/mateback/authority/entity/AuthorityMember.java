package me.emate.mateback.authority.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.emate.mateback.member.entity.Member;

@Table(name = "authority_member")
@Entity
@Getter
@NoArgsConstructor
public class AuthorityMember {
    @Id
    @Column(name = "authmem_no")
    private Integer authMemNo;

    @ManyToOne
    @JoinColumn(name = "authority_no")
    private Authority authority;

    @ManyToOne
    @JoinColumn(name = "member_no")
    private Member member;
}
