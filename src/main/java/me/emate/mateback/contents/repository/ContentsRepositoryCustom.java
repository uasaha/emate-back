package me.emate.mateback.contents.repository;

import java.util.List;
import me.emate.mateback.contents.dto.ContentsDetailResponseDto;
import me.emate.mateback.contents.dto.ContentsListResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * QueryDsl을 사용하기 위한 Contents repository custom입니다.
 *
 * @author 여운석
 */
@NoRepositoryBean
public interface ContentsRepositoryCustom {

  /**
   * Gets contents by contents no.
   *
   * @param contentsNo the contents no
   * @return the contents by contents no
   */
  ContentsDetailResponseDto getContentsByContentsNo(Integer contentsNo);

  /**
   * Gets contents by subject.
   *
   * @param subject the subject
   * @return the contents by subject
   */
  ContentsDetailResponseDto getContentsBySubject(String subject);

  /**
   * Gets latest contents.
   *
   * @return the latest contents
   */
  List<ContentsListResponseDto> getLatestContents();

  /**
   * Gets contents by category and pageable.
   *
   * @param categoryName the category name
   * @param pageable     the pageable
   * @return the contents by category and pageable
   */
  Page<ContentsListResponseDto> getContentsByCategoryAndPageable(String categoryName,
      Pageable pageable);

  /**
   * Gets contents by tag and pageable.
   *
   * @param tagName  the tag name
   * @param pageable the pageable
   * @return the contents by tag and pageable
   */
  Page<ContentsListResponseDto> getContentsByTagAndPageable(String tagName, Pageable pageable);

  /**
   * Gets total contents.
   *
   * @param pageable the pageable
   * @return the total contents
   */
  Page<ContentsListResponseDto> getTotalContents(Pageable pageable);

  /**
   * Gets contents contains subject.
   *
   * @param search   the search
   * @param pageable the pageable
   * @return the contents contains subject
   */
  Page<ContentsListResponseDto> getContentsContainsSubject(String search, Pageable pageable);
}
