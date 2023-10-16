package me.emate.mateback.category.repository.impl;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
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
        return from(category)
                .select(Projections.fields(
                        CategoryListResponseDto.class,
                        category.categoryNo,
                        category.categoryName,
                        ExpressionUtils.as(
                                JPAExpressions.select(contents.category.count())
                                        .from(contents)
                                        .where(contents.category.eq(category)
                                                .and(contents.isHidden.eq(false))
                                                .and(contents.isDeleted.eq(false))),
                                "contentsCnt")))
                        .where(category.isDeleted.eq(false))
                .fetch();
    }
}
