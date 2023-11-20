package me.emate.mateback.contents.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import me.emate.mateback.comment.dto.CommentListResponseDto;
import me.emate.mateback.comment.dummy.CommentDummy;
import me.emate.mateback.comment.entity.Comment;
import me.emate.mateback.comment.service.CommentService;
import me.emate.mateback.contents.dto.ContentsDetailResponseDto;
import me.emate.mateback.contents.dto.ContentsListResponseDto;
import me.emate.mateback.contents.dto.CreateContentsRequestDto;
import me.emate.mateback.contents.dummy.ContentsDummy;
import me.emate.mateback.contents.entity.Contents;
import me.emate.mateback.contents.service.ContentsService;
import me.emate.mateback.error.CustomAdviceController;
import me.emate.mateback.tag.dto.TagListResponseDto;
import me.emate.mateback.tag.dummy.TagDummy;
import me.emate.mateback.tag.entity.Tag;
import me.emate.mateback.utils.PageableResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ContentsController.class)
@Import(CustomAdviceController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureMockMvc(addFilters = false)
class ContentsControllerTest {

  @Autowired
  MockMvc mockMvc;
  ObjectMapper objectMapper;
  @MockBean
  ContentsService contentsService;
  @MockBean
  CommentService commentService;
  Contents contents;
  Tag tag;
  String contentsUrl = "/contents";

  @BeforeEach
  void setUp() {
    contents = ContentsDummy.dummy();
    tag = TagDummy.dummy();
    objectMapper = new ObjectMapper();
  }

  @Test
  @DisplayName("콘텐츠 등록 성공")
  void createContents() throws Exception {
    CreateContentsRequestDto requestDto = new CreateContentsRequestDto(
        contents.getContentsNo(),
        List.of(tag.getTagNo()),
        contents.isHidden(),
        contents.getThumbnail(),
        contents.getSubject(),
        contents.getDetail());

    doNothing().when(contentsService).createContents(requestDto);

    mockMvc.perform(post(contentsUrl)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(requestDto)))
        .andExpect(status().isCreated());
  }

  @Test
  @DisplayName("제목으로 콘텐츠 조회 성공")
  void getContentBySubject() throws Exception {
    ContentsDetailResponseDto responseDto = getContentsDetailResponseDto();

    when(contentsService.getContentsBySubject(contents.getSubject())).thenReturn(responseDto);

    mockMvc.perform(get(contentsUrl + "/" + contents.getSubject())
            .contentType(MediaType.APPLICATION_JSON)
            .pathInfo("/" + contents.getSubject()))
        .andExpect(jsonPath("$.contentsNo").value(contents.getContentsNo()))
        .andExpect(jsonPath("$.category").value(contents.getCategory().getCategoryName()))
        .andExpect(jsonPath("$.tags[0].tagNo").value(tag.getTagNo()))
        .andExpect(jsonPath("$.tags[0].tagName").value(tag.getTagName()))
        .andExpect(jsonPath("$.tags[0].tagColor").value(tag.getColor()))
        .andExpect(jsonPath("$.subject").value(contents.getSubject()))
        .andExpect(jsonPath("$.detail").value(contents.getDetail()))
        .andExpect(jsonPath("$.views").value(contents.getViews()))
        .andExpect(jsonPath("$.thumbnail").value(contents.getThumbnail()))
        .andExpect(jsonPath("$.hidden").value(contents.isHidden()))
        .andExpect(jsonPath("$.deleted").value(contents.isDeleted()))
        .andDo(print());
  }

  @Test
  @DisplayName("최신 콘텐츠 조회 성공")
  void getLatestContents() throws Exception {
    List<ContentsListResponseDto> contentsList = List.of(getContentsListResponseDto());

    when(contentsService.getLatestContents()).thenReturn(contentsList);

    mockMvc.perform(get(contentsUrl + "/latests")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.[0].thumbnail").value(contents.getThumbnail()))
        .andExpect(jsonPath("$.[0].subject").value(contents.getSubject()))
        .andExpect(jsonPath("$.[0].loving").value(contents.getLoving()))
        .andDo(print());
  }

  @Test
  @DisplayName("전체 콘텐츠 조회 성공")
  void getTotalContents() throws Exception {
    Pageable pageable = Pageable.ofSize(8);
    PageableResponse<ContentsListResponseDto> pageableResponse = getPageableResponse();

    when(contentsService.getTotalContents(pageable)).thenReturn(pageableResponse);

    mockMvc.perform(get(contentsUrl + "/totals")
        .param("page", "0")
        .param("size", "8"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.contents[0].thumbnail").value(contents.getThumbnail()))
        .andExpect(jsonPath("$.contents[0].subject").value(contents.getSubject()))
        .andExpect(jsonPath("$.contents[0].loving").value(contents.getLoving()))
        .andExpect(jsonPath("$.totalPages").value(pageableResponse.getTotalPages()))
        .andExpect(jsonPath("$.current").value(pageableResponse.getCurrent()))
        .andExpect(jsonPath("$.hasPrevious").value(pageableResponse.isHasPrevious()))
        .andExpect(jsonPath("$.hasNext").value(pageableResponse.isHasNext()))
        .andDo(print());
  }

  @Test
  @DisplayName("콘텐츠 검색 성공")
  void getContentsContainsSearch() throws Exception {
    Pageable pageable = Pageable.ofSize(8);
    PageableResponse<ContentsListResponseDto> page = getPageableResponse();

    when(contentsService.getContentsContainsSearch("du", pageable)).thenReturn(page);

    mockMvc.perform(get(contentsUrl + "/search")
            .param("key", "du")
            .param("page", "0")
            .param("size", "8"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.contents[0].thumbnail").value(contents.getThumbnail()))
        .andExpect(jsonPath("$.contents[0].subject").value(contents.getSubject()))
        .andExpect(jsonPath("$.contents[0].loving").value(contents.getLoving()))
        .andExpect(jsonPath("$.totalPages").value(page.getTotalPages()))
        .andExpect(jsonPath("$.current").value(page.getCurrent()))
        .andExpect(jsonPath("$.hasPrevious").value(page.isHasPrevious()))
        .andExpect(jsonPath("$.hasNext").value(page.isHasNext()))
        .andDo(print());
  }

  @Test
  @DisplayName("콘텐츠 댓글 조회 성공")
  void getCommentsByContentsNo() throws Exception {
    Comment comment = CommentDummy.dummy();
    List<CommentListResponseDto> responseDtoList =
        List.of(new CommentListResponseDto(
            comment.getCommentNo(),
            comment.getNickname(),
            comment.getContent(),
            comment.getCreatedAt(),
            comment.getModifiedAt(),
            comment.getMember().getMemberNo(),
            comment.isDeleted(),
            comment.isSecret(),
            List.of()));

    when(commentService.getCommentByContentsNo(contents.getContentsNo()))
        .thenReturn(responseDtoList);

    mockMvc.perform(get(contentsUrl + "/" + contents.getContentsNo() + "/comments")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.[0].commentNo").value(comment.getCommentNo()))
        .andExpect(jsonPath("$.[0].nickName").value(comment.getNickname()))
        .andExpect(jsonPath("$.[0].content").value(comment.getContent()))
        .andExpect(jsonPath("$.[0].modifiedAt").value(comment.getModifiedAt()))
        .andExpect(jsonPath("$.[0].memberNo").value(comment.getMember().getMemberNo()))
        .andExpect(jsonPath("$.[0].deleted").value(comment.isDeleted()))
        .andExpect(jsonPath("$.[0].secret").value(comment.isSecret()))
        .andDo(print());
  }

  private PageableResponse<ContentsListResponseDto> getPageableResponse() {
    Pageable pageable = Pageable.ofSize(8);
    Page<ContentsListResponseDto> page =
        PageableExecutionUtils.getPage(List.of(getContentsListResponseDto()), pageable, () -> 1L);

    return new PageableResponse<>(page);
  }

  private ContentsDetailResponseDto getContentsDetailResponseDto() {
    ContentsDetailResponseDto responseDto = new ContentsDetailResponseDto(
        contents.getContentsNo(),
        contents.getCategory().getCategoryName(),
        contents.isDeleted(),
        contents.isHidden(),
        contents.getSubject(),
        contents.getDetail(),
        contents.getViews(),
        contents.getLoving(),
        contents.getCreatedAt(),
        contents.getThumbnail());

    responseDto.setTags(
        List.of(new TagListResponseDto(tag.getTagNo(), tag.getTagName(), tag.getColor())));
    return responseDto;
  }

  private ContentsListResponseDto getContentsListResponseDto() {
    return new ContentsListResponseDto(
        contents.getThumbnail(),
        contents.getSubject(),
        contents.getCreatedAt(),
        contents.getLoving()
    );
  }
}