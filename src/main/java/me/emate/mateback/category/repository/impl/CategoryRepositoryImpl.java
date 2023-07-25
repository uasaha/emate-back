package me.emate.mateback.category.repository.impl;

import com.querydsl.core.types.Projections;
import me.emate.mateback.category.dto.CategoryListResponseDto;
import me.emate.mateback.category.entity.Category;
import me.emate.mateback.category.entity.QCategory;
import me.emate.mateback.category.repository.CategoryRepositoryCustom;
import me.emate.mateback.contents.entity.QContents;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class CategoryRepositoryImpl extends QuerydslRepositorySupport implements CategoryRepositoryCustom {
    public CategoryRepositoryImpl() {
        super(Category.class);
    }

    QCategory category = QCategory.category;
    QContents contents = QContents.contents;

    @Override
    public List<CategoryListResponseDto> findAllCategories() {
        List<CategoryListResponseDto> list =
                from(category)
                .select(Projections.constructor(
                        CategoryListResponseDto.class,
                        category.categoryNo,
                        category.categoryName))
                        .where(category.isDeleted.eq(false))
                .fetch();

        for(CategoryListResponseDto dto : list) {
            dto.setContentsCnt(
                    from(contents)
                            .select(contents.contentsNo)
                            .where(category.categoryNo.eq(dto.getCategoryNo()))
                            .fetchCount()
            );
        }

        return list;
    }
}
