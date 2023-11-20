package me.emate.mateback.statistic.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import me.emate.mateback.error.CustomAdviceController;
import me.emate.mateback.statistic.service.StatisticService;
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

@WebMvcTest(StatisticController.class)
@Import(CustomAdviceController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureMockMvc(addFilters = false)
class StatisticControllerTest {
  @Autowired
  MockMvc mockMvc;
  @MockBean
  StatisticService statisticService;

  String statisticUrl = "/visitors";

  @Test
  @DisplayName("전체 방문자 조회 성공")
  void getTotalVisitor() throws Exception {
    Integer totalVisitor = 10;

    when(statisticService.getTotalVisitor()).thenReturn(totalVisitor);

    mockMvc.perform(get(statisticUrl + "/total")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$").value(totalVisitor))
        .andExpect(status().isOk())
        .andDo(print());
  }

  @Test
  @DisplayName("투데이 저장 성공")
  void setTodayToTotal() throws Exception {
    String today = "10";

    doNothing().when(statisticService).setTodayToTotal(Integer.parseInt(today));

    mockMvc.perform(post(statisticUrl + "/" + today)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }
}