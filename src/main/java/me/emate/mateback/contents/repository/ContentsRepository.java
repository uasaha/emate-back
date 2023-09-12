package me.emate.mateback.contents.repository;

import me.emate.mateback.contents.entity.Contents;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentsRepository extends JpaRepository<Contents, Integer>, ContentsRepositoryCustom {
}
