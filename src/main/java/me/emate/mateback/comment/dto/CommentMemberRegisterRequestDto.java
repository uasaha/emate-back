package me.emate.mateback.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * The type Comment member register request dto.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentMemberRegisterRequestDto {

  private Integer contentsNo;
  private Integer memberNo;
  private Long momNo;
  private String content;
  private boolean secret;
}
