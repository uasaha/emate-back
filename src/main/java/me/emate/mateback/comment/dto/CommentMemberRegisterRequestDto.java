package me.emate.mateback.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
