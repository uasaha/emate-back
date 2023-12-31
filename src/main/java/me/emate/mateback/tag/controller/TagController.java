package me.emate.mateback.tag.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import me.emate.mateback.contents.dto.ContentsListResponseDto;
import me.emate.mateback.contents.service.ContentsService;
import me.emate.mateback.tag.dto.TagListResponseDto;
import me.emate.mateback.tag.service.TagService;
import me.emate.mateback.utils.PageableResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Tag controller입니다.
 *
 * @author 여운석
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/tags")
public class TagController {

  private final TagService tagService;
  private final ContentsService contentsService;

  /**
   * Find all tags response entity.
   *
   * @return the response entity
   */
  @GetMapping
  public ResponseEntity<List<TagListResponseDto>> findAllTags() {
    return ResponseEntity.ok().body(tagService.findAllTags());
  }

  /**
   * Create tag response entity.
   *
   * @param name the name
   * @return the response entity
   */
  @PostMapping
  public ResponseEntity<Void> createTag(@RequestBody String name) {
    tagService.createTag(name);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  /**
   * Delete tag response entity.
   *
   * @param tagNo the tag no
   * @return the response entity
   */
  @DeleteMapping
  public ResponseEntity<Void> deleteTag(@RequestParam Integer tagNo) {
    tagService.deleteTag(tagNo);
    return ResponseEntity.ok().build();
  }

  /**
   * Find contents by tag and pageable response entity.
   *
   * @param tagName  the tag name
   * @param pageable the pageable
   * @return the response entity
   */
  @GetMapping("{tagName}")
  public ResponseEntity<PageableResponse<ContentsListResponseDto>> findContentsByTagAndPageable(
      @PathVariable String tagName, Pageable pageable) {
    return ResponseEntity.ok()
        .body(contentsService.getContentsByTagAndPageable(tagName, pageable));
  }
}
