package me.emate.mateback.category.repository;

import me.emate.mateback.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * JPA를 사용하기 위한 Category repository.
 *
 * @author 여운석
 */
public interface CategoryRepository extends JpaRepository<Category, Integer>, CategoryRepositoryCustom {
    /**
     * 카테고리 명으로 카테고리 조회.
     *
     * @param name 카테고리 명
     * @return optional 객체
     */
    Optional<Category> findCategoryByCategoryName(String name);

    /**
     * 카테고리 번호로 카테고리 조회
     *
     * @param categoryNo 카테고리 번호
     * @return optional 객체
     */
    Optional<Category> findCategoryByCategoryNo(Integer categoryNo);
}
