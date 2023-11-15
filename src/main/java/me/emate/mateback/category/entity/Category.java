package me.emate.mateback.category.entity;

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
 * Category entity 입니다.
 *
 * @author 여운석
 */
@Table(name = "category")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "category_no")
  private Integer categoryNo;

  @Column(name = "category_name")
  private String categoryName;

  @Column(name = "category_deleted")
  private boolean isDeleted;

  /**
   * 삭제 요청시 삭제하는 메서드.
   */
  public void del() {
    this.isDeleted = true;
  }
}
