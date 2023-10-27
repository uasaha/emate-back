package me.emate.mateback.contents.service;

import me.emate.mateback.contents.dto.ContentsDetailResponseDto;
import me.emate.mateback.contents.dto.ContentsListResponseDto;
import me.emate.mateback.contents.dto.CreateContentsRequestDto;
import me.emate.mateback.utils.PageableResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ContentsService {
    void createContents(CreateContentsRequestDto requestDto);

    ContentsDetailResponseDto getContentsByNo(Integer contentsNo);

    ContentsDetailResponseDto getContentsBySubject(String subject);

    List<ContentsListResponseDto> getLatestContents();

    PageableResponse<ContentsListResponseDto> getContentsByCategoryAndPageable(String categoryName, Pageable pageable);

    PageableResponse<ContentsListResponseDto> getContentsByTagAndPageable(String tagName, Pageable pageable);

    PageableResponse<ContentsListResponseDto> getTotalContents(Pageable pageable);

    PageableResponse<ContentsListResponseDto> getContentsContainsSearch(String search, Pageable pageable);
}
