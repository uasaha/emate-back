package me.emate.mateback.comment.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.emate.mateback.contents.entity.Contents;
import me.emate.mateback.member.entity.Member;

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

  /**
   * Instantiates a new Comment.
   *
   * @param contents the contents
   * @param mom      the mom
   * @param content  the content
   * @param nickname the nickname
   * @param pwd      the pwd
   */
  @Builder(builderMethodName = "noMemberBuilder", builderClassName = "noMemberBuilder")
  public Comment(Contents contents, Comment mom, String content, String nickname, String pwd) {
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
    this.secret = false;
  }

  /**
   * Instantiates a new Comment.
   *
   * @param contents the contents
   * @param mom      the mom
   * @param content  the content
   * @param member   the member
   * @param nickname the nickname
   * @param secret   the secret
   */
  @Builder(builderMethodName = "memberBuilder", builderClassName = "memberBuilder")
  public Comment(Contents contents, Comment mom, String content, Member member, String nickname,
      boolean secret) {
    this.contents = contents;
    this.commentMom = mom;
    this.content = content;
    this.member = member;
    this.nickname = nickname;
    this.pwd = null;
    this.commentNo = null;
    this.createdAt = LocalDateTime.now();
    this.modifiedAt = null;
    this.deleted = false;
    this.secret = secret;
  }
}
