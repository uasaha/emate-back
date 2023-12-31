package me.emate.mateback.sympathy.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.emate.mateback.contents.entity.Contents;
import me.emate.mateback.member.entity.Member;

/**
 * Sympathy(공감) entity입니다.
 *
 * @author 여운석
 */
@Table(name = "sympathy")
@Entity
@NoArgsConstructor
@Getter
public class Sympathy {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private Long sympathyNo;

  @ManyToOne
  @JoinColumn(name = "member_no")
  private Member member;

  @ManyToOne
  @JoinColumn(name = "contents_no")
  private Contents contents;
}
