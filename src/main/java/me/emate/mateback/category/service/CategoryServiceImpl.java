package me.emate.mateback.category.service;

import lombok.RequiredArgsConstructor;
import me.emate.mateback.category.dto.CategoryListResponseDto;
import me.emate.mateback.category.entity.Category;
import me.emate.mateback.category.exception.CategoryNotFoundException;
import me.emate.mateback.category.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    @Override
    public List<CategoryListResponseDto> findAllCategories() {
        return categoryRepository.findAllCategories();
    }

    @Override
    public void createCategory(String name) {
        Category category = new Category(null, name, false);
        categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(String name) {
        Optional<Category> category = categoryRepository.findCategoryByCategoryName(name);

        if(category.isEmpty()) {
            throw new CategoryNotFoundException();
        }

        category.get().del();

        categoryRepository.save(category.get());
    }

}
