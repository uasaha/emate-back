package me.emate.mateback.contents.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.emate.mateback.category.entity.Category;
import me.emate.mateback.member.entity.Member;

/**
 * Contents entuty 입니다.
 *
 * @author 여운석
 */
@Table(name = "contents")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Contents {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "contents_no")
  private Integer contentsNo;

  @ManyToOne
  @JoinColumn(name = "category_no")
  private Category category;

  @ManyToOne
  @JoinColumn(name = "member_no")
  private Member member;

  @Column(name = "subject")
  private String subject;

  @Column(name = "detail")
  private String detail;

  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @Column(name = "contents_hidden")
  private boolean isHidden;

  @Column(name = "contents_deleted")
  private boolean isDeleted;

  @Column(name = "views")
  private Integer views;

  @Column(name = "loving")
  private Integer loving;

  @Column(name = "thumbnail")
  private String thumbnail;

  /**
   * Contents 생성시 사용하는 생성자.
   *
   * @param category  the category
   * @param member    the member
   * @param hidden    the hidden
   * @param subject   the subject
   * @param detail    the detail
   * @param thumbnail the thumbnail
   */
  @Builder
  public Contents(Category category, Member member, boolean hidden, String subject, String detail,
      String thumbnail) {
    this.category = category;
    this.member = member;
    this.subject = subject;
    this.detail = detail;
    this.views = 0;
    this.loving = 0;
    this.createdAt = LocalDateTime.now();
    this.thumbnail = thumbnail;
    this.isHidden = hidden;
  }
}
