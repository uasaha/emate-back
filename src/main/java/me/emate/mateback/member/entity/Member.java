package me.emate.mateback.member.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Builder
    public Member(String id, String pwd, String email, String nickname, String intro) {
        this.memberId = id;
        this.pwd = pwd;
        this.email = email;
        this.nickname = nickname;
        this.intro = intro;
    }
}
