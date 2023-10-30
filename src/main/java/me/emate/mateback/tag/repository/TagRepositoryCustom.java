package me.emate.mateback.tag.repository;

import me.emate.mateback.tag.dto.TagListResponseDto;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * Querydsl 사용을 위한 custom Tag repository 입니다.
 *
 * @author 여운석
 */
@NoRepositoryBean
public interface TagRepositoryCustom {
    /**
     * Find all tags list.
     *
     * @return the list
     */
    List<TagListResponseDto> findAllTags();
}
