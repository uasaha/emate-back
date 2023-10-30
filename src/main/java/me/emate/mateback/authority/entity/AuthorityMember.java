package me.emate.mateback.authority.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.emate.mateback.member.entity.Member;

/**
 * Authority member entity.
 *
 * @author 여운석
 */
@Table(name = "authority_member")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorityMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authmem_no")
    private Integer authMemNo;

    @ManyToOne
    @JoinColumn(name = "authority_no")
    private Authority authority;

    @ManyToOne
    @JoinColumn(name = "member_no")
    private Member member;
}
