package me.emate.mateback.tag.service;

import lombok.RequiredArgsConstructor;
import me.emate.mateback.tag.dto.TagListResponseDto;
import me.emate.mateback.tag.entity.Tag;
import me.emate.mateback.tag.exception.NotFoundTagException;
import me.emate.mateback.tag.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Tag service의 구현체 입니다.
 *
 * @author 여운석
 */
@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;
    private final static String DEFAULT_COLOR = "#252525";

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TagListResponseDto> findAllTags() {
        return tagRepository.findAllTags();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createTag(String name) {
        Tag tag = new Tag(null, name, false, DEFAULT_COLOR);
        tagRepository.save(tag);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteTag(Integer tagNo) {
        Optional<Tag> tag = tagRepository.findByTagNo(tagNo);

        if(tag.isEmpty()) {
            throw new NotFoundTagException();
        }

        tag.get().del();
    }
}
