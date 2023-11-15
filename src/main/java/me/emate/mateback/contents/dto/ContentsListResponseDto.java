package me.emate.mateback.contents.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Contents list response dto입니다.
 *
 * @author 여운석
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ContentsListResponseDto {

  private String thumbnail;
  private String subject;
  private LocalDateTime createdAt;
  private int loving;
}