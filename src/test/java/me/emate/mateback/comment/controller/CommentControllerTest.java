package me.emate.mateback.comment.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.emate.mateback.comment.dto.CommentMemberRegisterRequestDto;
import me.emate.mateback.comment.dto.CommentNoMemberRegisterRequestDto;
import me.emate.mateback.comment.dto.CommentResponseDto;
import me.emate.mateback.comment.dummy.CommentDummy;
import me.emate.mateback.comment.entity.Comment;
import me.emate.mateback.comment.service.CommentService;
import me.emate.mateback.contents.dummy.ContentsDummy;
import me.emate.mateback.contents.entity.Contents;
import me.emate.mateback.error.CustomAdviceController;
import me.emate.mateback.member.dummy.MemberDummy;
import me.emate.mateback.member.entity.Member;
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
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CommentController.class)
@Import(CustomAdviceController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureMockMvc(addFilters = false)
class CommentControllerTest {
  @Autowired
  MockMvc mockMvc;
  @MockBean
  CommentService commentService;
  ObjectMapper objectMapper;
  Comment comment;
  Contents contents;
  Member member;
  String commentUrl = "/comments";

  @BeforeEach
  void setUp() {
    this.objectMapper = new ObjectMapper();
    this.comment = CommentDummy.dummy();
    this.contents = ContentsDummy.dummy();
    this.member = MemberDummy.dummy();
  }

  @Test
  @DisplayName("비회원 댓글 등록 성공")
  void noMemberRegisterComment() throws Exception {
    CommentNoMemberRegisterRequestDto requestDto =
        new CommentNoMemberRegisterRequestDto(
            contents.getContentsNo(),
            0L,
            comment.getNickname(),
            comment.getPwd(),
            comment.getContent());

    CommentResponseDto commentResponseDto =
        new CommentResponseDto(
            comment.getCommentNo(),
            0L,
            comment.getNickname(),
            comment.getContent(),
            comment.getCreatedAt(),
            comment.getModifiedAt(),
            member.getMemberNo(),
            comment.isDeleted(),
            comment.isSecret());

    when(commentService.noMemberRegisterComment(any(CommentNoMemberRegisterRequestDto.class)))
        .thenReturn(commentResponseDto);

    mockMvc.perform(post(commentUrl + "/anonymous")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(requestDto)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.commentNo").value(commentResponseDto.getCommentNo()))
        .andExpect(jsonPath("$.momNo").value(commentResponseDto.getMomNo()))
        .andExpect(jsonPath("$.nickName").value(commentResponseDto.getNickName()))
        .andExpect(jsonPath("$.content").value(commentResponseDto.getContent()))
        .andExpect(jsonPath("$.deleted").value(commentResponseDto.isDeleted()))
        .andExpect(jsonPath("$.secret").value(commentResponseDto.isSecret()))
        .andDo(print());
  }

  @Test
  @DisplayName("회원 댓글 등록 성공")
  void memberRegisterComment() throws Exception {
    CommentMemberRegisterRequestDto requestDto =
        new CommentMemberRegisterRequestDto(
            contents.getContentsNo(),
            member.getMemberNo(),
            0L,
            comment.getContent(),
            comment.isSecret());

    doNothing().when(commentService).memberRegisterComment(requestDto);

    mockMvc.perform(post(commentUrl + "/members")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(requestDto)))
        .andExpect(status().isCreated())
        .andDo(print());
  }
}