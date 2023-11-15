package me.emate.mateback.contents.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import me.emate.mateback.tag.dto.TagListResponseDto;

/**
 * Contents detail response dto 입니다.
 *
 * @author 여운석
 */
@Getter
@ToString
public class ContentsDetailResponseDto {

  private Integer contentsNo;
  private String category;
  @Setter
  private List<TagListResponseDto> tags;
  private boolean isDeleted;
  private boolean isHidden;
  private String subject;
  private String detail;
  private Integer views;
  private Integer loving;
  private LocalDateTime createdAt;
  private String thumbnail;

  /**
   * Instantiates a new Contents detail response dto.
   *
   * @param contentsNo the contents no
   * @param category   the category
   * @param isDeleted  the is deleted
   * @param isHidden   the is hidden
   * @param subject    the subject
   * @param detail     the detail
   * @param views      the views
   * @param loving     the loving
   * @param createdAt  the created at
   * @param thumbnail  the thumbnail
   */
  public ContentsDetailResponseDto(Integer contentsNo, String category, boolean isDeleted,
      boolean isHidden, String subject, String detail, Integer views, Integer loving,
      LocalDateTime createdAt, String thumbnail) {
    this.contentsNo = contentsNo;
    this.category = category;
    this.isDeleted = isDeleted;
    this.isHidden = isHidden;
    this.subject = subject;
    this.detail = detail;
    this.views = views;
    this.loving = loving;
    this.createdAt = createdAt;
    this.thumbnail = thumbnail;
  }
}
