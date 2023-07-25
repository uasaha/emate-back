package me.emate.mateback.category.repository;

import me.emate.mateback.category.dto.CategoryListResponseDto;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface CategoryRepositoryCustom {
    List<CategoryListResponseDto> findAllCategories();
}
