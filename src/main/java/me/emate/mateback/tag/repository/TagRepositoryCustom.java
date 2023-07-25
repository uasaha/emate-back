package me.emate.mateback.tag.repository;

import me.emate.mateback.tag.dto.TagListResponseDto;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface TagRepositoryCustom {
    List<TagListResponseDto> findAllTags();
}
