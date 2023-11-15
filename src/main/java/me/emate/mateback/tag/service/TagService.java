package me.emate.mateback.tag.service;

import java.util.List;
import me.emate.mateback.tag.dto.TagListResponseDto;

/**
 * Tag service입니다.
 *
 * @author 여운석
 */
public interface TagService {

  /**
   * Find all tags list.
   *
   * @return the list
   */
  List<TagListResponseDto> findAllTags();

  /**
   * Create tag.
   *
   * @param name the name
   */
  void createTag(String name);

  /**
   * Delete tag.
   *
   * @param tagNo the tag no
   */
  void deleteTag(Integer tagNo);
}
