package me.emate.mateback.member.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.emate.mateback.member.dto.CheckEmailRequestDto;
import me.emate.mateback.member.dto.CheckIdRequestDto;
import me.emate.mateback.member.dto.CheckNicknameRequestDto;
import me.emate.mateback.member.dto.MemberDetailResponseDto;
import me.emate.mateback.member.dto.RegisterMemberRequestDto;
import me.emate.mateback.member.service.MemberService;
import me.emate.mateback.token.service.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Member controller 입니다.
 *
 * @author 여운석
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

  private final MemberService memberService;
  private final TokenService tokenService;

  /**
   * The constant AUTH_MEMBER_INFO.
   */
  public static final String AUTH_MEMBER_INFO = "X-Authorization-Id";
  /**
   * The constant TOKEN_CUT_NUM.
   */
  public static final Integer TOKEN_CUT_NUM = 7;


  /**
   * Signup response entity.
   *
   * @param requestDto the request dto
   * @return the response entity
   */
  @PostMapping("/signup")
  public ResponseEntity<Void> signup(@Valid @RequestBody RegisterMemberRequestDto requestDto) {
    memberService.signup(requestDto);

    return ResponseEntity.status(HttpStatus.CREATED)
        .build();
  }

  /**
   * Id conflict check response entity.
   *
   * @param requestDto the request dto
   * @return the response entity
   */
  @PostMapping("/signup/id")
  public ResponseEntity<Boolean> idConflictCheck(
      @RequestBody CheckIdRequestDto requestDto) {
    return ResponseEntity.ok()
        .body(memberService.isIdConflict(requestDto));
  }

  /**
   * Nick conflict check response entity.
   *
   * @param requestDto the request dto
   * @return the response entity
   */
  @PostMapping("/signup/nickname")
  public ResponseEntity<Boolean> nickConflictCheck(
      @RequestBody CheckNicknameRequestDto requestDto) {
    return ResponseEntity.ok()
        .body(memberService.isNickConflict(requestDto));
  }

  /**
   * Email conflict check response entity.
   *
   * @param requestDto the request dto
   * @return the response entity
   */
  @PostMapping("/signup/email")
  public ResponseEntity<Boolean> emailConflictCheck(
      @RequestBody CheckEmailRequestDto requestDto) {
    return ResponseEntity.ok()
        .body(memberService.isEmailConflict(requestDto));
  }

  /**
   * Gets member detail.
   *
   * @param request the request
   * @return the member detail
   */
  @GetMapping("/details")
  public ResponseEntity<MemberDetailResponseDto> getMemberDetail(HttpServletRequest request) {
    Integer memberNo = tokenService.getMemberNo(request);

    return ResponseEntity.status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(memberService.getMemberDetails(memberNo));
  }
}
