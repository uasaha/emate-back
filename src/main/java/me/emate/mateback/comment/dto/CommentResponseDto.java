package me.emate.mateback.comment.dto;


import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * The type Comment response dto.
 */
@AllArgsConstructor
@Getter
@Builder
public class CommentResponseDto {

  @NotNull
  private Long commentNo;
  private Long momNo;
  @NotNull
  private String nickName;
  @NotNull
  private String content;
  @NotNull
  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;
  private Integer memberNo;
  @NotNull
  private boolean deleted;
  @NotNull
  private boolean secret;
}
