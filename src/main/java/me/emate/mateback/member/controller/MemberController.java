package me.emate.mateback.member.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.emate.mateback.member.dto.*;
import me.emate.mateback.member.service.MemberService;
import me.emate.mateback.token.service.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    private final TokenService tokenService;

    public static final String AUTH_MEMBER_INFO = "X-Authorization-Id";
    public static final Integer TOKEN_CUT_NUM = 7;


    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@Valid @RequestBody RegisterMemberRequestDto requestDto) {
        memberService.signup(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }

    @PostMapping("/signup/idcheck")
    public ResponseEntity<Boolean> idConflictCheck(
            @RequestBody CheckIDRequestDto requestDto) {
        return ResponseEntity.ok()
                .body(memberService.isIdConflict(requestDto));
    }

    @PostMapping("/signup/nickcheck")
    public ResponseEntity<Boolean> nickConflictCheck(
            @RequestBody CheckNicknameRequestDto requestDto) {
        return ResponseEntity.ok()
                .body(memberService.isNickConflict(requestDto));
    }

    @PostMapping("/signup/emailcheck")
    public ResponseEntity<Boolean> emailConflictCheck(
            @RequestBody CheckEmailRequestDto requestDto) {
        return ResponseEntity.ok()
                .body(memberService.isEmailConflict(requestDto));
    }

    @GetMapping("/details")
    public ResponseEntity<MemberDetailResponseDto> getMemberDetail(HttpServletRequest request) {
        Integer memberNo = tokenService.getMemberNo(request);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(memberService.getMemberDetails(memberNo));
    }
}
