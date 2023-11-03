package me.emate.mateback.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
public class CommentListResponseDto {
    private Long commentNo;
    private String nickName;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Integer memberNo;
    private boolean deleted;
    private boolean secret;
    private List<CommentResponseDto> childComments;

    public CommentListResponseDto(CommentResponseDto commentResponseDto) {
        this.commentNo = commentResponseDto.getCommentNo();
        this.nickName = commentResponseDto.getNickName();
        this.content = commentResponseDto.getContent();
        this.createdAt = commentResponseDto.getCreatedAt();
        this.modifiedAt = commentResponseDto.getModifiedAt();
        this.memberNo = commentResponseDto.getMemberNo();
        this.deleted = commentResponseDto.isDeleted();
        this.secret = commentResponseDto.isSecret();
        this.childComments = new ArrayList<>();
    }
}
