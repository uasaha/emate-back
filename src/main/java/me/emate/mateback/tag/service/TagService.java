package me.emate.mateback.tag.service;

import me.emate.mateback.tag.dto.TagListResponseDto;

import java.util.List;

public interface TagService {
    List<TagListResponseDto> findAllTags();

    void createTag(String name);

    void deleteTag(Integer tagNo);
}
