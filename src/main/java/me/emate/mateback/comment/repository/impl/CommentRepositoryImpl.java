package me.emate.mateback.comment.repository.impl;

import com.querydsl.core.types.Projections;
import java.util.List;
import me.emate.mateback.comment.dto.CommentResponseDto;
import me.emate.mateback.comment.entity.Comment;
import me.emate.mateback.comment.entity.QComment;
import me.emate.mateback.comment.repository.CommentRepositoryCustom;
import me.emate.mateback.contents.entity.QContents;
import me.emate.mateback.member.entity.QMember;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;


/**
 * Querydsl 사용을 위한 Comment repository의 구현체입니다.
 *
 * @author 여운석
 */
public class CommentRepositoryImpl extends QuerydslRepositorySupport implements
    CommentRepositoryCustom {

  QComment comment = QComment.comment;
  QContents contents = QContents.contents;
  QMember member = QMember.member;

  /**
   * 생성자.
   */
  public CommentRepositoryImpl() {
    super(Comment.class);
  }

  @Override
  public List<CommentResponseDto> getCommentByContentsNo(Integer contentsNo) {
    return from(comment)
        .select(Projections.constructor(
            CommentResponseDto.class,
            comment.commentNo,
            comment.commentMom.commentNo,
            comment.nickname,
            comment.content,
            comment.createdAt,
            comment.modifiedAt,
            comment.member.memberNo,
            comment.deleted,
            comment.secret))
        .innerJoin(contents).on(comment.contents.eq(contents))
        .where(contents.contentsNo.eq(contentsNo))
        .orderBy(comment.createdAt.asc())
        .fetch();
  }
}
