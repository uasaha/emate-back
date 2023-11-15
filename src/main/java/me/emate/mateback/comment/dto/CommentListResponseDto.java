package me.emate.mateback.comment.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * The type Comment list response dto.
 */
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

  /**
   * Instantiates a new Comment list response dto.
   *
   * @param commentResponseDto the comment response dto
   */
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
