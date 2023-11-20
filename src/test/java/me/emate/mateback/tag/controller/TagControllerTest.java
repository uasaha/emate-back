package me.emate.mateback.tag.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import me.emate.mateback.contents.dto.ContentsListResponseDto;
import me.emate.mateback.contents.dummy.ContentsDummy;
import me.emate.mateback.contents.entity.Contents;
import me.emate.mateback.contents.service.ContentsService;
import me.emate.mateback.error.CustomAdviceController;
import me.emate.mateback.tag.dto.TagListResponseDto;
import me.emate.mateback.tag.dummy.TagDummy;
import me.emate.mateback.tag.entity.Tag;
import me.emate.mateback.tag.service.TagService;
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

@WebMvcTest(TagController.class)
@Import(CustomAdviceController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureMockMvc(addFilters = false)
class TagControllerTest {
  @Autowired
  MockMvc mockMvc;
  @MockBean
  TagService tagService;
  @MockBean
  ContentsService contentsService;

  Tag tag;
  ObjectMapper objectMapper;
  String tagUrl = "/tags";

  @BeforeEach
  void setUp() {
    this.tag = TagDummy.dummy();
    objectMapper = new ObjectMapper();
  }

  @Test
  @DisplayName("전체 태그 조회 성공")
  void findAllTags() throws Exception {
    TagListResponseDto responseDto = new TagListResponseDto(
        tag.getTagNo(),
        tag.getTagName(),
        tag.getColor());
    List<TagListResponseDto> responseDtoList = List.of(responseDto);

    when(tagService.findAllTags()).thenReturn(responseDtoList);

    mockMvc.perform(get(tagUrl)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.[0].tagNo").value(tag.getTagNo()))
        .andExpect(jsonPath("$.[0].tagName").value(tag.getTagName()))
        .andExpect(jsonPath("$.[0].tagColor").value(tag.getColor()))
        .andDo(print());
  }

  @Test
  @DisplayName("태그 등록 성공")
  void createTag() throws Exception {
    doNothing().when(tagService).createTag(tag.getTagName());

    mockMvc.perform(post(tagUrl)
        .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(tag.getTagName())))
        .andExpect(status().isCreated());
  }

  @Test
  @DisplayName("태그 삭제 성공")
  void deleteTag() throws Exception {
    doNothing().when(tagService).deleteTag(tag.getTagNo());

    mockMvc.perform(delete(tagUrl)
            .param("tagNo", String.valueOf(tag.getTagNo()))
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  @DisplayName("태그 명으로 콘텐츠 조회 성공")
  void findContentsByTagAndPageable() throws Exception {
    Contents contents = ContentsDummy.dummy();
    Pageable pageable = Pageable.ofSize(8);
    ContentsListResponseDto contentsListResponseDto = new ContentsListResponseDto(
        contents.getThumbnail(),
        contents.getSubject(),
        contents.getCreatedAt(),
        contents.getLoving());

    Page<ContentsListResponseDto> page =
        PageableExecutionUtils.getPage(List.of(contentsListResponseDto), pageable, () -> 1L);

    PageableResponse<ContentsListResponseDto> pageableResponse =
        new PageableResponse<>(page);

    when(contentsService.getContentsByTagAndPageable(tag.getTagName(), pageable))
        .thenReturn(pageableResponse);

    mockMvc.perform(get(tagUrl + "/" + tag.getTagName())
        .contentType(MediaType.APPLICATION_JSON)
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
}