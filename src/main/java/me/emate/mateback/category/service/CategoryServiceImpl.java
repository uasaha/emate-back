package me.emate.mateback.category.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import me.emate.mateback.category.dto.CategoryListResponseDto;
import me.emate.mateback.category.entity.Category;
import me.emate.mateback.category.exception.CategoryNotFoundException;
import me.emate.mateback.category.repository.CategoryRepository;
import org.springframework.stereotype.Service;

/**
 * Category service의 구현체입니다.
 *
 * @author 여운석
 */
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;

  /**
   * {@inheritDoc}
   */
  @Override
  public List<CategoryListResponseDto> findAllCategories() {
    return categoryRepository.findAllCategories();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void createCategory(String name) {
    Category category = new Category(null, name, false);
    categoryRepository.save(category);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void deleteCategory(String name) {
    Optional<Category> category = categoryRepository.findCategoryByCategoryName(name);

    if (category.isEmpty()) {
      throw new CategoryNotFoundException();
    }

    category.get().del();

    categoryRepository.save(category.get());
  }

}
