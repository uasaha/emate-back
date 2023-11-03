package me.emate.mateback.comment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.emate.mateback.comment.dto.CommentListResponseDto;
import me.emate.mateback.comment.dto.CommentNoMemberRegisterRequestDto;
import me.emate.mateback.comment.dto.CommentResponseDto;
import me.emate.mateback.comment.entity.Comment;
import me.emate.mateback.comment.repository.CommentRepository;
import me.emate.mateback.contents.entity.Contents;
import me.emate.mateback.contents.exception.NotFoundContentsException;
import me.emate.mateback.contents.repository.ContentsRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Comment service의 구현체 입니다.
 *
 * @author 여운석
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final ContentsRepository contentsRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public CommentResponseDto noMemberRegisterComment(CommentNoMemberRegisterRequestDto requestDto) {
        Comment mom = getMomIfHasMom(requestDto);

        Contents contents = contentsRepository.findById(requestDto.getContentsNo())
                .orElseThrow(NotFoundContentsException::new);

        Comment comment = Comment.builder()
                .mom(mom)
                .contents(contents)
                .nickname(requestDto.getNickName())
                .pwd(passwordEncoder.encode(requestDto.getPassword()))
                .content(requestDto.getContent())
                .secret(requestDto.isSecret())
                .build();

        Comment reponseComment = commentRepository.save(comment);


        return CommentResponseDto.builder()
                .commentNo(reponseComment.getCommentNo())
                .nickName(reponseComment.getNickname())
                .content(reponseComment.getContent())
                .createdAt(reponseComment.getCreatedAt())
                .deleted(reponseComment.isDeleted())
                .secret(reponseComment.isSecret())
                .build();
    }

    @Override
    public List<CommentListResponseDto> getCommentByContentsNo(Integer contentsNo) {
        List<CommentResponseDto> commentList =
                commentRepository.getCommentByContentsNo(contentsNo);

        List<CommentListResponseDto> responseDtoList = new ArrayList<>();

        commentList.stream().filter(x -> x.getMomNo() == null)
                .forEach(x -> responseDtoList.add(new CommentListResponseDto(x)));

        responseDtoList.forEach(r -> commentList.stream()
                                .filter(x -> x.getMomNo() != null)
                                .filter(x -> x.getMomNo().equals(r.getCommentNo()))
                                .sorted((x, y) -> Math.toIntExact((x.getCommentNo() - y.getCommentNo())))
                                .forEach(x -> r.getChildComments().add(x)));

        return responseDtoList;
    }

    private Comment getMomIfHasMom(CommentNoMemberRegisterRequestDto requestDto) {
        if (requestDto.getMomNo() != null) {
            return commentRepository.findById(requestDto.getMomNo()).orElse(null);
        }
        return null;
    }

}
