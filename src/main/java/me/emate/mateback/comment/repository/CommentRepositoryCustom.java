package me.emate.mateback.comment.repository;

import me.emate.mateback.comment.dto.CommentResponseDto;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * Querydsl 사용을 위한 Comment repository custom입니다.
 *
 * @author 여운석
 */
@NoRepositoryBean
public interface CommentRepositoryCustom {
    List<CommentResponseDto> getCommentByContentsNo(Integer contentsNo);
}
