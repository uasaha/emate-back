package me.emate.mateback.comment.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
 * The type Comment no member register request dto.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CommentNoMemberRegisterRequestDto {
  @NotNull
  private Integer contentsNo;

  private Long momNo;

  @Length(min = 2, max = 8)
  private String nickName;

  @Length(min = 4, max = 8)
  private String password;

  @Length(max = 500)
  private String content;
}
