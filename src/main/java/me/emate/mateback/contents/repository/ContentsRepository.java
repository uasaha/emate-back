package me.emate.mateback.contents.repository;

import me.emate.mateback.contents.entity.Contents;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA를 사용하기 위한 Contents repository.
 *
 * @author 여운석
 */
public interface ContentsRepository extends JpaRepository<Contents, Integer>, ContentsRepositoryCustom {
}
