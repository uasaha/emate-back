package me.emate.mateback.contentstag.repository;

import me.emate.mateback.contentstag.entity.ContentsTag;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA사용을 위한 Contents tag repository 입니다.
 *
 * @author 여운석
 */
public interface ContentsTagRepository extends JpaRepository<ContentsTag, Integer> {
}
