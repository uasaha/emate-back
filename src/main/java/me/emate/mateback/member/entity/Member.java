package me.emate.mateback.member.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.emate.mateback.authority.entity.AuthorityMember;

import java.util.HashSet;
import java.util.Set;

/**
 * Member entity 입니다.
 *
 * @author 여운석
 */
@Table(name = "member")
@Entity
@Getter
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_no")
    private Integer memberNo;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "email")
    private String email;

    @Column(name = "member_id")
    private String memberId;

    @Column(name = "pwd")
    private String pwd;

    @Column(name = "member_blocked")
    private boolean isBlocked;

    @Column(name = "member_left")
    private boolean isLeft;

    @Column(name = "intro")
    private String intro;

    @OneToMany(mappedBy = "member", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<AuthorityMember> authorities = new HashSet<>();

    /**
     * Instantiates a new Member.
     *
     * @param id       the id
     * @param pwd      the pwd
     * @param email    the email
     * @param nickname the nickname
     * @param intro    the intro
     */
    @Builder
    public Member(String id, String pwd, String email, String nickname, String intro) {
        this.memberId = id;
        this.pwd = pwd;
        this.email = email;
        this.nickname = nickname;
        this.intro = intro;
    }
}
