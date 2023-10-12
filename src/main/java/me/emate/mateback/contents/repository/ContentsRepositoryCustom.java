package me.emate.mateback.contents.repository;

import me.emate.mateback.contents.dto.ContentsDetailResponseDto;
import me.emate.mateback.contents.dto.ContentsListResponseDto;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;
import java.util.List;

@NoRepositoryBean
public interface ContentsRepositoryCustom {
    Optional<ContentsDetailResponseDto> getContentsByContentsNo(Integer contentsNo);

    Optional<ContentsDetailResponseDto> getContentsBySubject(String subject);

    Optional<ContentsDetailResponseDto> getLatestContent();

    List<ContentsListResponseDto> getLatestContents();
}
