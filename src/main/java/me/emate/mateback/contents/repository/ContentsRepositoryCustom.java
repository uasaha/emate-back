package me.emate.mateback.contents.repository;

import me.emate.mateback.contents.dto.ContentsDetailResponseDto;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface ContentsRepositoryCustom {
    Optional<ContentsDetailResponseDto> getContentsByContentsNo(Integer contentsNo);

}
