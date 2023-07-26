package me.emate.mateback.member.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.emate.mateback.member.dto.*;
import me.emate.mateback.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/register")
    public ResponseEntity<MemberNicknameResponseDto> registerMember(
            @Valid @RequestBody RegisterMemberRequestDto requestDto) {
        MemberNicknameResponseDto responseDto =
                memberService.memberRegister(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(responseDto);
    }

    @GetMapping("/login")
    public ResponseEntity<MemberNicknameResponseDto> loginMember(
            @Valid @RequestBody LoginMemberRequestDto requestDto) {
        MemberNicknameResponseDto responseDto =
                memberService.memberLogin(requestDto);

        return ResponseEntity.ok()
                .body(responseDto);
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
}
