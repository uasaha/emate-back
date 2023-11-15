package me.emate.mateback.contents.service;

import java.util.List;
import me.emate.mateback.contents.dto.ContentsDetailResponseDto;
import me.emate.mateback.contents.dto.ContentsListResponseDto;
import me.emate.mateback.contents.dto.CreateContentsRequestDto;
import me.emate.mateback.utils.PageableResponse;
import org.springframework.data.domain.Pageable;

/**
 * Contents service 입니다.
 *
 * @author 여운석
 */
public interface ContentsService {

  /**
   * Create contents.
   *
   * @param requestDto the request dto
   */
  void createContents(CreateContentsRequestDto requestDto);

  /**
   * Gets contents by no.
   *
   * @param contentsNo the contents no
   * @return the contents by no
   */
  ContentsDetailResponseDto getContentsByNo(Integer contentsNo);

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
  PageableResponse<ContentsListResponseDto> getContentsByCategoryAndPageable(String categoryName,
      Pageable pageable);

  /**
   * Gets contents by tag and pageable.
   *
   * @param tagName  the tag name
   * @param pageable the pageable
   * @return the contents by tag and pageable
   */
  PageableResponse<ContentsListResponseDto> getContentsByTagAndPageable(String tagName,
      Pageable pageable);

  /**
   * Gets total contents.
   *
   * @param pageable the pageable
   * @return the total contents
   */
  PageableResponse<ContentsListResponseDto> getTotalContents(Pageable pageable);

  /**
   * Gets contents contains search.
   *
   * @param search   the search
   * @param pageable the pageable
   * @return the contents contains search
   */
  PageableResponse<ContentsListResponseDto> getContentsContainsSearch(String search,
      Pageable pageable);
}
