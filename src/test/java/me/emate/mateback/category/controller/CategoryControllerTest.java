package me.emate.mateback.category.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.emate.mateback.category.dto.CategoryListResponseDto;
import me.emate.mateback.category.dummy.CategoryDummy;
import me.emate.mateback.category.entity.Category;
import me.emate.mateback.category.service.CategoryService;
import me.emate.mateback.contents.dto.ContentsListResponseDto;
import me.emate.mateback.contents.dummy.ContentsDummy;
import me.emate.mateback.contents.entity.Contents;
import me.emate.mateback.contents.service.ContentsService;
import me.emate.mateback.error.CustomAdviceController;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryController.class)
@Import(CustomAdviceController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureMockMvc(addFilters = false)
class CategoryControllerTest {

  @Autowired
  MockMvc mockMvc;
  @MockBean
  CategoryService categoryService;
  @MockBean
  ContentsService contentsService;
  ObjectMapper objectMapper;
  Category category;
  Contents contents;
  CategoryListResponseDto categoryListResponseDto;
  String categoryUrl = "/category";

  @BeforeEach
  void setUp() {
    category = CategoryDummy.dummy();
    contents = ContentsDummy.dummy();
    categoryListResponseDto = new CategoryListResponseDto(category.getCategoryNo(),
        category.getCategoryName(), 10L);

    objectMapper = new ObjectMapper();


  }

  @Test
  @DisplayName("전체 카테고리 조회 성공")
  void findAllCategories() throws Exception {
    List<CategoryListResponseDto> responseDtoList = List.of(categoryListResponseDto);

    when(categoryService.findAllCategories())
        .thenReturn(responseDtoList);

    mockMvc.perform(get(categoryUrl)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.[0].categoryNo").value(categoryListResponseDto.getCategoryNo()))
        .andExpect(jsonPath("$.[0].categoryName").value(categoryListResponseDto.getCategoryName()))
        .andExpect(jsonPath("$.[0].contentsCnt").value(categoryListResponseDto.getContentsCnt()));
  }

  @Test
  @DisplayName("카테고리 생성 성공")
  void createCategory() throws Exception {
    doNothing().when(categoryService).createCategory("dummy");

    mockMvc.perform(post(categoryUrl)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString("dummy")))
        .andExpect(status().isCreated());
  }

  @Test
  @DisplayName("카테고리 삭제 성공")
  void deleteCategory() throws Exception {
    doNothing().when(categoryService).deleteCategory("dummy");

    mockMvc.perform(delete(categoryUrl)
            .contentType(MediaType.APPLICATION_JSON)
            .param("name", "dummy"))
        .andExpect(status().isOk());
  }

  @Test
  @DisplayName("카테고리로 컨텐츠 조회 성공")
  void findContentsByCategoryAndPageable() throws Exception {
    ContentsListResponseDto responseDto =
        new ContentsListResponseDto(contents.getThumbnail(),
            contents.getSubject(),
            contents.getCreatedAt(),
            contents.getLoving());
    List<ContentsListResponseDto> responseDtoList = List.of(responseDto);
    Pageable pageable = PageRequest.of(0, 8);
    Page<ContentsListResponseDto> result =
        PageableExecutionUtils.getPage(responseDtoList, pageable, () -> 1L);
    PageableResponse<ContentsListResponseDto> pageableResponse =
        new PageableResponse<>(result);

    when(contentsService.getContentsByCategoryAndPageable(
        category.getCategoryName(), Pageable.ofSize(8)))
        .thenReturn(pageableResponse);

    mockMvc.perform(get(categoryUrl + "/dummy")
            .param("page", objectMapper.writeValueAsString(pageable.getPageNumber()))
            .param("size", objectMapper.writeValueAsString(pageable.getPageSize()))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.totalPages").value(result.getTotalPages()))
        .andExpect(jsonPath("$.current").value(result.getNumber()))
        .andExpect(jsonPath("$.hasPrevious").value(result.hasPrevious()))
        .andExpect(jsonPath("$.hasNext").value(result.hasNext()))
        .andExpect(jsonPath("$.contents[0].thumbnail").value(responseDto.getThumbnail()))
        .andExpect(jsonPath("$.contents[0].subject").value(responseDto.getSubject()))
        .andExpect(jsonPath("$.contents[0].loving").value(responseDto.getLoving()));
  }
}