package me.emate.mateback.contents.repository.impl;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import java.util.List;
import me.emate.mateback.category.entity.QCategory;
import me.emate.mateback.contents.dto.ContentsDetailResponseDto;
import me.emate.mateback.contents.dto.ContentsListResponseDto;
import me.emate.mateback.contents.entity.Contents;
import me.emate.mateback.contents.entity.QContents;
import me.emate.mateback.contents.repository.ContentsRepositoryCustom;
import me.emate.mateback.contentstag.entity.QContentsTag;
import me.emate.mateback.tag.dto.TagListResponseDto;
import me.emate.mateback.tag.entity.QTag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.support.PageableExecutionUtils;

/**
 * QueryDsl 사용을 위한 Contents repository의 구현체 입니다.
 *
 * @author 여운석
 */
public class ContentsRepositoryImpl extends QuerydslRepositorySupport implements
    ContentsRepositoryCustom {

  /**
   * 생성자.
   */
  public ContentsRepositoryImpl() {
    super(Contents.class);
  }

  QContents contents = QContents.contents;
  QCategory category = QCategory.category;
  QTag tag = QTag.tag;
  QContentsTag contentsTag = QContentsTag.contentsTag;

  /**
   * {@inheritDoc}
   */
  @Override
  public ContentsDetailResponseDto getContentsByContentsNo(Integer contentsNo) {
    ContentsDetailResponseDto responseDto = from(contents)
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
        .fetchOne();

    List<TagListResponseDto> tagList = from(tag)
        .select(Projections.constructor(
            TagListResponseDto.class,
            tag.tagNo,
            tag.tagName,
            tag.color
        ))
        .leftJoin(contentsTag)
        .on(contentsTag.tag.eq(tag))
        .leftJoin(contents).on(contents.eq(contentsTag.contents))
        .where(contents.contentsNo.eq(responseDto.getContentsNo()))
        .where(contentsTag.contents.contentsNo.eq(contents.contentsNo))
        .where(tag.isDeleted.eq(Boolean.FALSE))
        .fetch();

    responseDto.setTags(tagList);

    return responseDto;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ContentsDetailResponseDto getContentsBySubject(String subject) {
    ContentsDetailResponseDto responseDto = from(contents)
        .select(Projections.constructor(
            ContentsDetailResponseDto.class,
            contents.contentsNo,
            ExpressionUtils.as(
                JPAExpressions.select(category.categoryName)
                    .from(category)
                    .where(contents.category.categoryNo.eq(category.categoryNo)),
                "category"),
            contents.isDeleted,
            contents.isHidden,
            contents.subject,
            contents.detail,
            contents.views,
            contents.loving,
            contents.createdAt,
            contents.thumbnail))
        .where(contents.subject.eq(subject))
        .fetchOne();

    List<TagListResponseDto> tagList = from(tag)
        .select(Projections.constructor(
            TagListResponseDto.class,
            tag.tagNo,
            tag.tagName,
            tag.color
        ))
        .leftJoin(contentsTag)
        .on(contentsTag.tag.eq(tag))
        .leftJoin(contents).on(contents.eq(contentsTag.contents))
        .where(contents.contentsNo.eq(responseDto.getContentsNo()))
        .where(contentsTag.contents.contentsNo.eq(contents.contentsNo))
        .where(tag.isDeleted.eq(Boolean.FALSE))
        .distinct()
        .fetch();

    responseDto.setTags(tagList);

    return responseDto;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<ContentsListResponseDto> getLatestContents() {
    return from(contents)
        .select(Projections.fields(
            ContentsListResponseDto.class,
            contents.thumbnail,
            contents.subject,
            contents.createdAt,
            contents.loving))
        .orderBy(contents.createdAt.desc())
        .where(contents.isDeleted.eq(false).and(contents.isHidden.eq(false)))
        .limit(8L).fetch();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Page<ContentsListResponseDto> getContentsByCategoryAndPageable(String categoryName,
      Pageable pageable) {
    List<ContentsListResponseDto> responses = from(contents)
        .select(Projections.fields(
            ContentsListResponseDto.class,
            contents.thumbnail,
            contents.subject,
            contents.createdAt,
            contents.loving))
        .orderBy(contents.createdAt.desc())
        .where(contents.isDeleted.eq(false).and(contents.isHidden.eq(false)))
        .innerJoin(category).on(category.eq(contents.category))
        .where(contents.category.categoryName.eq(categoryName))
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize()).fetch();

    JPQLQuery<Long> count = from(contents).select(contents.count())
        .innerJoin(category).on(contents.category.eq(category))
        .where(contents.isDeleted.eq(false).and(contents.isHidden.eq(false)))
        .where(contents.category.categoryName.eq(categoryName));

    return PageableExecutionUtils.getPage(responses, pageable, count::fetchOne);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Page<ContentsListResponseDto> getContentsByTagAndPageable(String tagName,
      Pageable pageable) {
    List<ContentsListResponseDto> responses = from(contents)
        .select(Projections.fields(
            ContentsListResponseDto.class,
            contents.thumbnail,
            contents.subject,
            contents.createdAt,
            contents.loving))
        .orderBy(contents.createdAt.desc())
        .where(contents.isDeleted.eq(false).and(contents.isHidden.eq(false)))
        .innerJoin(contentsTag).on(contentsTag.contents.eq(contents))
        .innerJoin(tag).on(contentsTag.tag.eq(tag))
        .where(tag.tagName.eq(tagName))
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize()).fetch();

    JPQLQuery<Long> count = from(contents).select(contents.count())
        .innerJoin(contentsTag).on(contentsTag.contents.eq(contents))
        .innerJoin(tag).on(contentsTag.tag.eq(tag))
        .where(contents.isDeleted.eq(false).and(contents.isHidden.eq(false)))
        .where(tag.tagName.eq(tagName));

    return PageableExecutionUtils.getPage(responses, pageable, count::fetchOne);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Page<ContentsListResponseDto> getTotalContents(Pageable pageable) {
    List<ContentsListResponseDto> responses = from(contents)
        .select(Projections.fields(
            ContentsListResponseDto.class,
            contents.thumbnail,
            contents.subject,
            contents.createdAt,
            contents.loving))
        .orderBy(contents.createdAt.desc())
        .where(contents.isDeleted.eq(false).and(contents.isHidden.eq(false)))
        .innerJoin(category).on(category.eq(contents.category))
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize()).fetch();

    JPQLQuery<Long> count = from(contents).select(contents.count())
        .innerJoin(category).on(contents.category.eq(category))
        .where(contents.isDeleted.eq(false).and(contents.isHidden.eq(false)));

    return PageableExecutionUtils.getPage(responses, pageable, count::fetchOne);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Page<ContentsListResponseDto> getContentsContainsSubject(String search,
      Pageable pageable) {
    List<ContentsListResponseDto> responses = from(contents)
        .select(Projections.fields(
            ContentsListResponseDto.class,
            contents.thumbnail,
            contents.subject,
            contents.createdAt,
            contents.loving))
        .where(contents.subject.contains(search))
        .orderBy(contents.createdAt.desc())
        .where(contents.isDeleted.eq(false).and(contents.isHidden.eq(false)))
        .innerJoin(category).on(category.eq(contents.category))
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize()).fetch();

    JPQLQuery<Long> count = from(contents).select(contents.count())
        .innerJoin(category).on(contents.category.eq(category))
        .where(contents.subject.contains(search))
        .where(contents.isDeleted.eq(false).and(contents.isHidden.eq(false)));

    return PageableExecutionUtils.getPage(responses, pageable, count::fetchOne);
  }
}
