package me.emate.mateback.comment.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.emate.mateback.contents.entity.Contents;
import me.emate.mateback.member.entity.Member;

import java.time.LocalDateTime;

/**
 * Comment entity입니다.
 *
 * @author 여운석
 */
@Table(name = "comment")
@Entity
@Getter
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_no")
    private Long commentNo;

    @ManyToOne
    @JoinColumn(name = "contents_no")
    private Contents contents;

    @ManyToOne
    @JoinColumn(name = "comment_mom")
    private Comment commentMom;

    @ManyToOne
    @JoinColumn(name = "member_no")
    private Member member;

    @Column(name = "comment_content")
    private String content;

    @Column(name = "nomember_nickname")
    private String nickname;

    @Column(name = "nomember_pwd")
    private String pwd;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @Column(name = "comment_deleted")
    private boolean deleted;

    @Column(name = "comment_secret")
    private boolean secret;

    @Builder
    public Comment(Contents contents, Comment mom, String content, String nickname, String pwd, boolean secret) {
        this.contents = contents;
        this.commentMom = mom;
        this.content = content;
        this.nickname = nickname;
        this.pwd = pwd;
        this.commentNo = null;
        this.member = null;
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = null;
        this.deleted = false;
        this.secret = secret;
    }
}
