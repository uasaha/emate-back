package me.emate.mateback.contents.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.emate.mateback.category.entity.Category;
import me.emate.mateback.category.exception.CategoryNotFoundException;
import me.emate.mateback.category.repository.CategoryRepository;
import me.emate.mateback.contents.dto.ContentsDetailResponseDto;
import me.emate.mateback.contents.dto.CreateContentsRequestDto;
import me.emate.mateback.contents.entity.Contents;
import me.emate.mateback.contents.exception.NotFoundContentsException;
import me.emate.mateback.contents.repository.ContentsRepository;
import me.emate.mateback.member.entity.Member;
import me.emate.mateback.member.exception.NotFoundMemberException;
import me.emate.mateback.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ContentsServiceImpl implements ContentsService {
    private final ContentsRepository contentsRepository;
    private final CategoryRepository categoryRepository;
    private final MemberRepository memberRepository;

    @Override
    public ContentsDetailResponseDto createContents(CreateContentsRequestDto requestDto) {
        Category category = categoryRepository
                .findCategoryByCategoryNo(requestDto.getCategoryNo())
                .orElseThrow(CategoryNotFoundException::new);

        Member member = memberRepository.findMemberByMemberId("rayoung7421")
                .orElseThrow(NotFoundMemberException::new);

        Contents contents = contentsRepository.save(
                Contents.builder()
                .category(category)
                .member(member)
                .subject(requestDto.getSubject())
                .detail(requestDto.getDetail())
                .build());

        ContentsDetailResponseDto responseDto =
                contentsRepository.getContentsByContentsNo(contents.getContentsNo())
                        .orElseThrow(NotFoundContentsException::new);

        return responseDto;
    }
}
