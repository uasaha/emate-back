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
import me.emate.mateback.contents.exception.NotFoundContentsException;
import me.emate.mateback.contents.repository.ContentsRepository;
import me.emate.mateback.contentsTag.repository.ContentsTagRepository;
import me.emate.mateback.member.entity.Member;
import me.emate.mateback.member.exception.NotFoundMemberException;
import me.emate.mateback.member.repository.MemberRepository;
import me.emate.mateback.tag.entity.Tag;
import me.emate.mateback.tag.exception.NotFoundTagException;
import me.emate.mateback.tag.repository.TagRepository;
import me.emate.mateback.utils.PageableResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Transactional
    @Override
    public ContentsDetailResponseDto createContents(CreateContentsRequestDto requestDto) {
        Category category = categoryRepository
                .findCategoryByCategoryNo(requestDto.getCategoryNo())
                .orElseThrow(CategoryNotFoundException::new);

        Member member = memberRepository.findById(1)
                .orElseThrow(NotFoundMemberException::new);

        Contents contents = contentsRepository.save(
                Contents.builder()
                .category(category)
                .member(member)
                .thumbnail(requestDto.getThumbnail())
                .subject(requestDto.getSubject())
                .detail(requestDto.getDetail())
                .build());

        Tag tag = tagRepository.findByTagNo(requestDto.getTagNo())
                .orElseThrow(NotFoundTagException::new);

        contentsTagRepository.save(
                new ContentsTag(null, contents, tag));

        return contentsRepository.getContentsBySubject(contents.getSubject())
                .orElseThrow(NotFoundContentsException::new);
    }

    @Override
    public ContentsDetailResponseDto getContentsByNo(Integer contentsNo) {
        return contentsRepository.getContentsByContentsNo(contentsNo)
                .orElseThrow(NotFoundContentsException::new);
    }

    @Override
    public ContentsDetailResponseDto getContentsBySubject(String subject) {
        String blankSubject = subject.replace("-", " ");
        return contentsRepository.getContentsBySubject(blankSubject)
                .orElseThrow(NotFoundContentsException::new);
    }

    @Override
    public ContentsDetailResponseDto getLatestContent() {
        return contentsRepository.getLatestContent()
                .orElseThrow(NotFoundContentsException::new);
    }

    @Override
    public List<ContentsListResponseDto> getLatestContents() {
        return contentsRepository.getLatestContents();
    }

    @Override
    public PageableResponse<ContentsListResponseDto> getContentsByCategoryAndPageable(String categoryName, Pageable pageable) {
        return new PageableResponse<>(contentsRepository.getContentsByCategoryAndPageable(categoryName, pageable));
    }

    @Override
    public PageableResponse<ContentsListResponseDto> getTotalContents(Pageable pageable) {
        return new PageableResponse<>(contentsRepository.getTotalContents(pageable));
    }
}
