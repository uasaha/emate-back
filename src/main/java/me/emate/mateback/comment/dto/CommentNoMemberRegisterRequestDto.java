package me.emate.mateback.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * The type Comment no member register request dto.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CommentNoMemberRegisterRequestDto {

  private Integer contentsNo;
  private Long momNo;
  private String nickName;
  private String password;
  private String content;
}
