package me.emate.mateback.category.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Category list를 반환하기 위한 response dto.
 *
 * @author 여운석
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CategoryListResponseDto {

  private Integer categoryNo;
  private String categoryName;
  private Long contentsCnt;
}
