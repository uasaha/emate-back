package me.emate.mateback.comment.service;

import me.emate.mateback.comment.dto.CommentListResponseDto;
import me.emate.mateback.comment.dto.CommentMemberRegisterRequestDto;
import me.emate.mateback.comment.dto.CommentNoMemberRegisterRequestDto;
import me.emate.mateback.comment.dto.CommentResponseDto;

import java.util.List;

/**
 * Comment service입니다.
 *
 * @author 여운석
 */
public interface CommentService {

    /**
     * 비회원 댓글 등록
     *
     * @param requestDto 등록할 댓글 내용
     * @return 등록된 댓글
     */
    CommentResponseDto noMemberRegisterComment(CommentNoMemberRegisterRequestDto requestDto);

    List<CommentListResponseDto> getCommentByContentsNo(Integer contentsNo);

    void memberRegisterComment(CommentMemberRegisterRequestDto requestDto);
}
