package me.emate.mateback.tag.repository;

import java.util.Optional;
import me.emate.mateback.tag.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA를 사용하기 위한 Tag repository입니다.
 *
 * @author 여운석
 */
public interface TagRepository extends JpaRepository<Tag, Integer>, TagRepositoryCustom {

  /**
   * Find by tag no optional.
   *
   * @param tagNo the tag no
   * @return the optional
   */
  Optional<Tag> findByTagNo(Integer tagNo);
}
