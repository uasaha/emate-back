package me.emate.mateback.contents.repository.impl;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import me.emate.mateback.category.entity.QCategory;
import me.emate.mateback.contents.dto.ContentsDetailResponseDto;
import me.emate.mateback.contents.entity.Contents;
import me.emate.mateback.contents.entity.QContents;
import me.emate.mateback.contents.repository.ContentsRepositoryCustom;
import me.emate.mateback.contentsTag.entity.QContentsTag;
import me.emate.mateback.tag.entity.QTag;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.Optional;

public class ContentsRepositoryImpl extends QuerydslRepositorySupport implements ContentsRepositoryCustom {
    public ContentsRepositoryImpl() {
        super(Contents.class);
    }
    QContents contents = QContents.contents;
    QCategory category = QCategory.category;
    QTag tag = QTag.tag;
    QContentsTag contentsTag = QContentsTag.contentsTag;

    public Optional<ContentsDetailResponseDto> getContentsByContentsNo(Integer contentsNo) {
        return Optional
                .of(from(contents)
                        .select(Projections.constructor(
                                ContentsDetailResponseDto.class,
                                contents.contentsNo,
                                ExpressionUtils.as(
                                        JPAExpressions.select(category.categoryName)
                                                .from(category)
                                                .where(contents.category.categoryNo.eq(category.categoryNo)),
                                        "category"),
                                ExpressionUtils.as(
                                        JPAExpressions.select(tag.tagName)
                                                .from(tag)
                                                .leftJoin(contentsTag)
                                                .on(contentsTag.tag.tagNo.eq(tag.tagNo))
                                                .where(contentsTag.contents.contentsNo.eq(contents.contentsNo)),
                                                "tags"),
                                contents.isDeleted,
                                contents.isHidden,
                                contents.subject,
                                contents.detail,
                                contents.views,
                                contents.loving,
                                contents.createdAt))
                        .where(contents.contentsNo.eq(contentsNo))
                        .fetchOne());
    }

    @Override
    public Optional<ContentsDetailResponseDto> getContentsBySubject(String subject) {
        return Optional
                .of(from(contents)
                        .select(Projections.constructor(
                                ContentsDetailResponseDto.class,
                                contents.contentsNo,
                                ExpressionUtils.as(
                                        JPAExpressions.select(category.categoryName)
                                                .from(category)
                                                .where(contents.category.categoryNo.eq(category.categoryNo)),
                                        "category"),
                                ExpressionUtils.as(
                                        JPAExpressions.select(tag.tagName)
                                                .from(tag)
                                                .leftJoin(contentsTag)
                                                .on(contentsTag.tag.tagNo.eq(tag.tagNo))
                                                .where(contentsTag.contents.contentsNo.eq(contents.contentsNo)),
                                        "tags"),
                                contents.isDeleted,
                                contents.isHidden,
                                contents.subject,
                                contents.detail,
                                contents.views,
                                contents.loving,
                                contents.createdAt))
                        .where(contents.subject.eq(subject))
                        .fetchOne());
    }

    @Override
    public Optional<ContentsDetailResponseDto> getLatestContent() {
        return Optional
                .of(from(contents)
                        .select(Projections.constructor(
                                ContentsDetailResponseDto.class,
                                contents.contentsNo,
                                ExpressionUtils.as(
                                        JPAExpressions.select(category.categoryName)
                                                .from(category)
                                                .where(contents.category.categoryNo.eq(category.categoryNo)),
                                        "category"),
                                ExpressionUtils.as(
                                        JPAExpressions.select(tag.tagName)
                                                .from(tag)
                                                .leftJoin(contentsTag)
                                                .on(contentsTag.tag.tagNo.eq(tag.tagNo))
                                                .where(contentsTag.contents.contentsNo.eq(contents.contentsNo)),
                                        "tags"),
                                contents.isDeleted,
                                contents.isHidden,
                                contents.subject,
                                contents.detail,
                                contents.views,
                                contents.loving,
                                contents.createdAt))
                        .orderBy(contents.createdAt.desc())
                        .where(contents.isDeleted.eq(false).and(contents.isHidden.eq(false)))
                        .limit(1L)
                        .fetchOne());

        //SELECT * FROM "TABLE NAME" ORDER BY "COLUMN NAME" DESC LIMIT 1
    }
}
