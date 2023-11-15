package me.emate.mateback.contents.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.emate.mateback.contents.dto.ContentsDetailResponseDto;
import me.emate.mateback.contents.dto.ContentsListResponseDto;
import me.emate.mateback.contents.dto.CreateContentsRequestDto;
import me.emate.mateback.contents.service.ContentsService;
import me.emate.mateback.utils.PageableResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Contents controller입니다.
 *
 * @author 여운석
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/contents")
public class ContentsController {

  private final ContentsService contentsService;

  /**
   * Create contents response entity.
   *
   * @param requestDto the request dto
   * @return the response entity
   */
  @PostMapping("/register")
  public ResponseEntity<Void> createContents(
      @RequestBody CreateContentsRequestDto requestDto) {
    log.info(String.valueOf(requestDto.getHidden()));

    contentsService.createContents(requestDto);

    return ResponseEntity
        .status(HttpStatus.CREATED).build();
  }

  /**
   * Gets content by subject.
   *
   * @param subject the subject
   * @return the content by subject
   */
  @GetMapping("/{subject}")
  public ResponseEntity<ContentsDetailResponseDto> getContentBySubject(
      @PathVariable String subject) {
    return ResponseEntity
        .ok(contentsService.getContentsBySubject(subject));
  }

  /**
   * Gets latest contents.
   *
   * @return the latest contents
   */
  @GetMapping("/latests")
  public ResponseEntity<List<ContentsListResponseDto>> getLatestContents() {
    return ResponseEntity.ok(contentsService.getLatestContents());
  }

  /**
   * Gets total contents.
   *
   * @param pageable the pageable
   * @return the total contents
   */
  @GetMapping("/total")
  public ResponseEntity<PageableResponse<ContentsListResponseDto>>
        getTotalContents(@PageableDefault(size = 8) Pageable pageable) {
    return ResponseEntity.ok().body(contentsService.getTotalContents(pageable));
  }

  /**
   * Gets contents contains search.
   *
   * @param key      the key
   * @param pageable the pageable
   * @return the contents contains search
   */
  @GetMapping("/search")
  public ResponseEntity<PageableResponse<ContentsListResponseDto>>
        getContentsContainsSearch(@RequestParam("key") String key,
      @PageableDefault(size = 8) Pageable pageable) {
    return ResponseEntity.ok().body(contentsService.getContentsContainsSearch(key, pageable));
  }
}
