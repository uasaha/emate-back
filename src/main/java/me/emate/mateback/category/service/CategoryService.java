package me.emate.mateback.category.service;

import java.util.List;
import me.emate.mateback.category.dto.CategoryListResponseDto;

/**
 * The interface Category service.
 *
 * @author 여운석
 */
public interface CategoryService {

  /**
   * 모든 카테고리 조회.
   *
   * @return 카테고리 dto list
   */
  List<CategoryListResponseDto> findAllCategories();

  /**
   * 카테고리 생성.
   *
   * @param name 카테고리 명
   */
  void createCategory(String name);

  /**
   * 카테고리 삭제.
   *
   * @param name 카테고리 명
   */
  void deleteCategory(String name);
}
