package me.emate.mateback.comment.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

/**
 * The type Comment response dto.
 */
@AllArgsConstructor
@Getter
@Builder
@Schema(description = "단일 댓글 response")
public class CommentResponseDto {

  @NotNull
  @Schema(description = "댓글 번호", defaultValue = "5")
  private Long commentNo;

  @Schema(description = "모댓글 번호", defaultValue = "3", nullable = true)
  private Long momNo;

  @NotNull
  @Schema(description = "댓글 닉네임", defaultValue = "nickname")
  private String nickName;

  @Length(min = 1, max = 500)
  @Schema(description = "댓글 내용", defaultValue = "좋은 글입니다!")
  private String content;

  @NotNull
  @Schema(description = "작성 일시", defaultValue = "2023-01-01 12:00")
  private LocalDateTime createdAt;

  @Schema(description = "수정 일시", defaultValue = "2023-01-01 12:10")
  private LocalDateTime modifiedAt;

  @Schema(description = "회원일 때 회원 번호", defaultValue = "1", nullable = true)
  private Integer memberNo;

  @NotNull
  @Schema(description = "삭제 여부", defaultValue = "false")
  private boolean deleted;

  @NotNull
  @Schema(description = "비밀 댓글 여부", defaultValue = "false")
  private boolean secret;
}


