package me.emate.mateback.category.repository;

import me.emate.mateback.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer>, CategoryRepositoryCustom {
    Optional<Category> findCategoryByCategoryName(String name);
    Optional<Category> findCategoryByCategoryNo(Integer categoryNo);
}
