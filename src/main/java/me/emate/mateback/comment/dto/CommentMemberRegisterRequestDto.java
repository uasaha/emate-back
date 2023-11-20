package me.emate.mateback.comment.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
 * The type Comment member register request dto.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentMemberRegisterRequestDto {
  @NotNull(message = "글 번호가 누락되었습니다.")
  private Integer contentsNo;
  @NotNull(message = "회원 번호가 누락되었습니다.")
  private Integer memberNo;
  private Long momNo;
  @Length(max = 500, message = "댓글은 최대 500자입니다.")
  private String content;
  @NotNull(message = "비밀여부가 누락되었습니다.")
  private boolean secret;
}
