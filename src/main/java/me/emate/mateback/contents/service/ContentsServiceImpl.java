package me.emate.mateback.contents.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.emate.mateback.category.entity.Category;
import me.emate.mateback.category.exception.CategoryNotFoundException;
import me.emate.mateback.category.repository.CategoryRepository;
import me.emate.mateback.contents.dto.ContentsDetailResponseDto;
import me.emate.mateback.contents.dto.ContentsListResponseDto;
import me.emate.mateback.contents.dto.CreateContentsRequestDto;
import me.emate.mateback.contents.entity.Contents;
import me.emate.mateback.contentsTag.entity.ContentsTag;
import me.emate.mateback.contents.repository.ContentsRepository;
import me.emate.mateback.contentsTag.repository.ContentsTagRepository;
import me.emate.mateback.member.entity.Member;
import me.emate.mateback.member.exception.MemberNotFoundException;
import me.emate.mateback.member.repository.MemberRepository;
import me.emate.mateback.tag.entity.Tag;
import me.emate.mateback.tag.exception.NotFoundTagException;
import me.emate.mateback.tag.repository.TagRepository;
import me.emate.mateback.utils.PageableResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Contents service의 구현체 입니다.
 *
 * @author 여운석
 */
@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ContentsServiceImpl implements ContentsService {
    private final ContentsRepository contentsRepository;
    private final CategoryRepository categoryRepository;
    private final MemberRepository memberRepository;
    private final TagRepository tagRepository;
    private final ContentsTagRepository contentsTagRepository;

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public void createContents(CreateContentsRequestDto requestDto) {
        Category category = categoryRepository
                .findCategoryByCategoryNo(requestDto.getCategoryNo())
                .orElseThrow(CategoryNotFoundException::new);

        Member member = memberRepository.findById(1)
                .orElseThrow(MemberNotFoundException::new);

        Contents contents = contentsRepository.save(
                Contents.builder()
                .category(category)
                .hidden(requestDto.getHidden())
                .member(member)
                .thumbnail(requestDto.getThumbnail())
                .subject(requestDto.getSubject())
                .detail(requestDto.getDetail())
                .build());

        for (Integer tagNo : requestDto.getTagNo()) {
            Tag tag = tagRepository.findByTagNo(tagNo)
                    .orElseThrow(NotFoundTagException::new);

            contentsTagRepository.save(
                    new ContentsTag(null, contents, tag));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ContentsDetailResponseDto getContentsByNo(Integer contentsNo) {
        return contentsRepository.getContentsByContentsNo(contentsNo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ContentsDetailResponseDto getContentsBySubject(String subject) {
        String blankSubject = subject.replace("-", " ");
        return contentsRepository.getContentsBySubject(blankSubject);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ContentsListResponseDto> getLatestContents() {
        return contentsRepository.getLatestContents();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageableResponse<ContentsListResponseDto> getContentsByCategoryAndPageable(String categoryName, Pageable pageable) {
        return new PageableResponse<>(contentsRepository.getContentsByCategoryAndPageable(categoryName, pageable));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageableResponse<ContentsListResponseDto> getContentsByTagAndPageable(String tagName, Pageable pageable) {
        String blankTag = tagName.replace("-", " ");

        return new PageableResponse<>(contentsRepository.getContentsByTagAndPageable(blankTag, pageable));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageableResponse<ContentsListResponseDto> getTotalContents(Pageable pageable) {
        return new PageableResponse<>(contentsRepository.getTotalContents(pageable));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageableResponse<ContentsListResponseDto> getContentsContainsSearch(String search, Pageable pageable) {
        return new PageableResponse<>(contentsRepository.getContentsContainsSubject(search, pageable));
    }
}
