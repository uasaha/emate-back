package me.emate.mateback.comment.repository;

import me.emate.mateback.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA를 사용하기 위한 Comment repository 입니다.
 *
 * @author 여운석
 */
public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
