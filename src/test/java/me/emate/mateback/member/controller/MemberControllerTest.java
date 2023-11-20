package me.emate.mateback.member.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import me.emate.mateback.authority.dummy.AuthorityDummy;
import me.emate.mateback.authority.dummy.AuthorityMemberDummy;
import me.emate.mateback.authority.entity.Authority;
import me.emate.mateback.authority.entity.AuthorityMember;
import me.emate.mateback.error.CustomAdviceController;
import me.emate.mateback.member.dto.CheckEmailRequestDto;
import me.emate.mateback.member.dto.CheckIdRequestDto;
import me.emate.mateback.member.dto.CheckNicknameRequestDto;
import me.emate.mateback.member.dto.MemberDetailResponseDto;
import me.emate.mateback.member.dto.RegisterMemberRequestDto;
import me.emate.mateback.member.dummy.MemberDummy;
import me.emate.mateback.member.entity.Member;
import me.emate.mateback.member.service.MemberService;
import me.emate.mateback.token.service.TokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(MemberController.class)
@Import(CustomAdviceController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureMockMvc(addFilters = false)
class MemberControllerTest {
  @Autowired
  MockMvc mockMvc;
  @MockBean
  MemberService memberService;
  @MockBean
  TokenService tokenService;
  ObjectMapper objectMapper;
  Member member;
  String memberUrl = "/members";
  HttpServletRequest httpServletRequest;
  @BeforeEach
  void setUp() {
    this.objectMapper = new ObjectMapper();
    httpServletRequest = new MockHttpServletRequest();

    Authority authority = AuthorityDummy.dummy();
    Member memberBefore = MemberDummy.dummy();
    AuthorityMember authorityMember = AuthorityMemberDummy.dummy(authority, memberBefore);
    member = MemberDummy.dummy(authorityMember);
  }

  @Test
  @DisplayName("회원가입 성공")
  void signup() throws Exception {
    RegisterMemberRequestDto requestDto = new RegisterMemberRequestDto();
    ReflectionTestUtils.setField(requestDto, "memberId", member.getMemberId());
    ReflectionTestUtils.setField(requestDto, "pwd", member.getPwd());
    ReflectionTestUtils.setField(requestDto, "email", member.getEmail());
    ReflectionTestUtils.setField(requestDto, "nickname", member.getNickname());
    ReflectionTestUtils.setField(requestDto, "intro", member.getIntro());

    doNothing().when(memberService).signup(requestDto);

    mockMvc.perform(post(memberUrl + "/signup")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(requestDto)))
        .andExpect(status().isCreated())
        .andDo(print());
  }

  @Test
  @DisplayName("아이디 중복 검사 성공")
  void idConflictCheck() throws Exception {
    CheckIdRequestDto requestDto = new CheckIdRequestDto(member.getMemberId());

    when(memberService.isIdConflict(requestDto)).thenReturn(Boolean.FALSE);

    mockMvc.perform(post(memberUrl + "/signup/id")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(requestDto)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").value(Boolean.FALSE))
        .andDo(print());
  }

  @Test
  @DisplayName("닉네임 중복 검사 성공")
  void nickConflictCheck() throws Exception {
    CheckNicknameRequestDto requestDto = new CheckNicknameRequestDto(member.getNickname());

    when(memberService.isNickConflict(requestDto)).thenReturn(Boolean.FALSE);

    mockMvc.perform(post(memberUrl + "/signup/nickname")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(requestDto)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").value(Boolean.FALSE))
        .andDo(print());
  }

  @Test
  @DisplayName("이메일 중복 검사 성공")
  void emailConflictCheck() throws Exception {
    CheckEmailRequestDto requestDto = new CheckEmailRequestDto(member.getEmail());

    when(memberService.isEmailConflict(requestDto)).thenReturn(Boolean.FALSE);

    mockMvc.perform(post(memberUrl + "/signup/email")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(requestDto)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").value(Boolean.FALSE))
        .andDo(print());
  }

  @Test
  @DisplayName("회원 상세 정보 조회 성공")
  void getMemberDetail() throws Exception {
    MemberDetailResponseDto responseDto = new MemberDetailResponseDto(member);

    when(tokenService.getMemberNo(httpServletRequest)).thenReturn(member.getMemberNo());
    when(memberService.getMemberDetails(member.getMemberNo())).thenReturn(responseDto);

    mockMvc.perform(get(memberUrl + "/details")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andDo(print());
  }
}