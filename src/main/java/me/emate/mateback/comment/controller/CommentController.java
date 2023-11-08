package me.emate.mateback.comment.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.emate.mateback.comment.dto.CommentListResponseDto;
import me.emate.mateback.comment.dto.CommentMemberRegisterRequestDto;
import me.emate.mateback.comment.dto.CommentNoMemberRegisterRequestDto;
import me.emate.mateback.comment.dto.CommentResponseDto;
import me.emate.mateback.comment.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/anonymous")
    public ResponseEntity<CommentResponseDto> noMemberRegisterComment(@RequestBody CommentNoMemberRegisterRequestDto requestDto) {
        CommentResponseDto responseDto = commentService.noMemberRegisterComment(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PostMapping("/member")
    public ResponseEntity<Void> memberRegisterComment(@RequestBody CommentMemberRegisterRequestDto requestDto) {
        commentService.memberRegisterComment(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/contents/{contentsNo}")
    public ResponseEntity<List<CommentListResponseDto>> getCommentsByContentsNo(@PathVariable Integer contentsNo) {
        return ResponseEntity.ok().body(commentService.getCommentByContentsNo(contentsNo));
    }
}
