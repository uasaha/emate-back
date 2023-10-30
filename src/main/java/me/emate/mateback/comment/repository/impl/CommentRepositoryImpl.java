package me.emate.mateback.comment.repository.impl;

import me.emate.mateback.comment.entity.Comment;
import me.emate.mateback.comment.repository.CommentRepositoryCustom;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

/**
 * Querydsl 사용을 위한 Comment repository의 구현체입니다.
 *
 * @author 여운석
 */
public class CommentRepositoryImpl extends QuerydslRepositorySupport implements CommentRepositoryCustom {

    /**
     * 생성자.
     */
    public CommentRepositoryImpl() {
        super(Comment.class);
    }
}
