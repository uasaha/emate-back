package me.emate.mateback.comment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.emate.mateback.contents.entity.Contents;
import me.emate.mateback.member.entity.Member;

import java.time.LocalDateTime;

@Table(name = "comment")
@Entity
@Getter
@NoArgsConstructor
public class Comment {
    @Id
    @Column(name = "comment_no")
    private Long commentNo;

    @ManyToOne
    @JoinColumn(name = "contents_no")
    private Contents contents;

    @ManyToOne
    @JoinColumn(name = "comment_mom")
    private Comment comment;

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
    private boolean isDeleted;
}