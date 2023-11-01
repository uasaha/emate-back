package me.emate.mateback.category.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.emate.mateback.category.dto.CategoryListResponseDto;
import me.emate.mateback.category.dummy.CategoryDummy;
import me.emate.mateback.category.entity.Category;
import me.emate.mateback.category.service.CategoryService;
import me.emate.mateback.contents.dummy.ContentsDummy;
import me.emate.mateback.contents.entity.Contents;
import me.emate.mateback.contents.service.ContentsService;
import me.emate.mateback.error.CustomAdviceController;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

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
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void createCategory() {

    }

    @Test
    void deleteCategory() {
    }

    @Test
    void findContentsByCategoryAndPageable() {
    }
}