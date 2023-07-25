package me.emate.mateback.tag.repository;

import me.emate.mateback.tag.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface TagRepository extends JpaRepository<Tag, Integer>, TagRepositoryCustom {
    Optional<Tag> findByTagNo(Integer tagNo);
}
