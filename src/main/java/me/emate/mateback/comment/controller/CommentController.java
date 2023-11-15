package me.emate.mateback.comment.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.emate.mateback.comment.dto.CommentListResponseDto;
import me.emate.mateback.comment.dto.CommentMemberRegisterRequestDto;
import me.emate.mateback.comment.dto.CommentNoMemberRegisterRequestDto;
import me.emate.mateback.comment.dto.CommentResponseDto;
import me.emate.mateback.comment.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Comment controller 입니다.
 *
 * @author 여운석
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

  private final CommentService commentService;

  /**
   * No member register comment response entity.
   *
   * @param requestDto the request dto
   * @return the response entity
   */
  @PostMapping("/anonymous")
  public ResponseEntity<CommentResponseDto> noMemberRegisterComment(
      @RequestBody CommentNoMemberRegisterRequestDto requestDto) {
    CommentResponseDto responseDto = commentService.noMemberRegisterComment(requestDto);

    return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
  }

  /**
   * Member register comment response entity.
   *
   * @param requestDto the request dto
   * @return the response entity
   */
  @PostMapping("/member")
  public ResponseEntity<Void> memberRegisterComment(
      @RequestBody CommentMemberRegisterRequestDto requestDto) {
    commentService.memberRegisterComment(requestDto);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  /**
   * Gets comments by contents no.
   *
   * @param contentsNo the contents no
   * @return the comments by contents no
   */
  @GetMapping("/contents/{contentsNo}")
  public ResponseEntity<List<CommentListResponseDto>> getCommentsByContentsNo(
      @PathVariable Integer contentsNo) {
    List<CommentListResponseDto> list = commentService.getCommentByContentsNo(contentsNo);
    StringBuilder sb = new StringBuilder();
    list.forEach(x -> x.getChildComments()
        .forEach(y -> sb.append(y.getCreatedAt().toString()).append("\n")));
    log.info(sb.toString());
    return ResponseEntity.ok().body(commentService.getCommentByContentsNo(contentsNo));
  }
}
