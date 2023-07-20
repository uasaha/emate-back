package me.emate.mateback.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "member")
@Entity
@Getter
@NoArgsConstructor
public class Member {
    @Id
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
    private Boolean isBlocked;

    @Column(name = "member_left")
    private Boolean isLeft;

    @Column(name = "intro")
    private String intro;
}
