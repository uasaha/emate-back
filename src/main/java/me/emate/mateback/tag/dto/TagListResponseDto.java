package me.emate.mateback.tag.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Tag를 list로 받기위한 response dto입니다.
 *
 * @author 여운석
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TagListResponseDto {

  private Integer tagNo;
  private String tagName;
  private String tagColor;

  /**
   * Instantiates a new Tag list response dto.
   *
   * @param tagNo   the tag no
   * @param tagName the tag name
   */
  public TagListResponseDto(Integer tagNo, String tagName) {
    this.tagNo = tagNo;
    this.tagName = tagName;
  }
}
