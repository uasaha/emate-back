package me.emate.mateback.comment.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.emate.mateback.comment.service.CommentService;
import org.springframework.web.bind.annotation.RestController;

/**
 * Comment controller 입니다.
 *
 * @author 여운석
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
}
