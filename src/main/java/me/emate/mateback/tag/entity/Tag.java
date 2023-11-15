package me.emate.mateback.tag.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Tag entity 입니다.
 *
 * @author 여운석
 */
@Table(name = "tag")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Tag {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private Integer tagNo;

  @Column
  private String tagName;

  @Column(name = "tag_deleted")
  private boolean isDeleted;

  @Column
  private String color;

  /**
   * Del.
   */
  public void del() {
    this.isDeleted = true;
  }
}
