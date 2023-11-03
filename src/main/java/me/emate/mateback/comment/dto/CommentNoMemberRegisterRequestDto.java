package me.emate.mateback.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CommentNoMemberRegisterRequestDto {
    private Integer contentsNo;
    private Long momNo;
    private String nickName;
    private String password;
    private String content;
    private boolean secret;
}
