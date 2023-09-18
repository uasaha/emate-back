package me.emate.mateback.comment.repository.impl;

import me.emate.mateback.comment.entity.Comment;
import me.emate.mateback.comment.repository.CommentRepositoryCustom;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class CommentRepositoryImpl extends QuerydslRepositorySupport implements CommentRepositoryCustom {

    public CommentRepositoryImpl() {
        super(Comment.class);
    }
}
