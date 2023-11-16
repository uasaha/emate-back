package me.emate.mateback.comment.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.emate.mateback.comment.dto.CommentMemberRegisterRequestDto;
import me.emate.mateback.comment.dto.CommentNoMemberRegisterRequestDto;
import me.emate.mateback.comment.dto.CommentResponseDto;
import me.emate.mateback.comment.service.CommentService;
import me.emate.mateback.error.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Comment controller 입니다.
 *
 * @author 여운석
 */
@Tag(name = "Comment", description = "댓글 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

  private final CommentService commentService;

  /**
   * 비회원의 댓글을 등록합니다.
   *
   * @param requestDto the request dto
   * @return the response entity
   */
  @Operation(summary = "비회원 댓글 등록", description = "비회원으로 댓글을 등록합니다.")
  @ApiResponse(responseCode = "201", description = "댓글 등록 성공",
      content = @Content(schema = @Schema(implementation = CommentResponseDto.class)))
  @ApiResponse(responseCode = "404", description = "댓글을 시도하는 글 번호가 잘못 됨",
      content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
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
  @PostMapping("/members")
  public ResponseEntity<Void> memberRegisterComment(
      @RequestBody CommentMemberRegisterRequestDto requestDto) {
    commentService.memberRegisterComment(requestDto);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}
