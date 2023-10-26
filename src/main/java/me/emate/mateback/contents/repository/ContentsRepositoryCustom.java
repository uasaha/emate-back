package me.emate.mateback.contents.repository;

import me.emate.mateback.contents.dto.ContentsDetailResponseDto;
import me.emate.mateback.contents.dto.ContentsListResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface ContentsRepositoryCustom {
    ContentsDetailResponseDto getContentsByContentsNo(Integer contentsNo);

    ContentsDetailResponseDto getContentsBySubject(String subject);

    List<ContentsListResponseDto> getLatestContents();

    Page<ContentsListResponseDto> getContentsByCategoryAndPageable(String categoryName, Pageable pageable);

    Page<ContentsListResponseDto> getContentsByTagAndPageable(String tagName, Pageable pageable);

    Page<ContentsListResponseDto> getTotalContents(Pageable pageable);

    Page<ContentsListResponseDto> getContentsContainsSubject(String search, Pageable pageable);
}
