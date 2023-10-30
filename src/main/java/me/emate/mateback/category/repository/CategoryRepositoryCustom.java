package me.emate.mateback.category.repository;

import me.emate.mateback.category.dto.CategoryListResponseDto;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * QueryDsl 사용을 위한 Category repository custom.
 *
 * @author 여운석
 */
@NoRepositoryBean
public interface CategoryRepositoryCustom {
    /**
     * 모든 카테고리 조회.
     *
     * @return 카테고리 dto list
     */
    List<CategoryListResponseDto> findAllCategories();
}
