package me.emate.mateback.token.controller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.emate.mateback.error.CustomAdviceController;
import me.emate.mateback.token.service.TokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(TokenController.class)
@Import(CustomAdviceController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureMockMvc(addFilters = false)
class TokenControllerTest {
  @Autowired
  MockMvc mockMvc;
  @MockBean
  TokenService tokenService;
  ObjectMapper objectMapper;

  @BeforeEach
  void setUp() {
    objectMapper = new ObjectMapper();
  }

  @Test
  void tokenReIssued() throws Exception {
    String accessToken = "accessToken";
    String result = "re-accessToken";
    when(tokenService.tokenReIssued(anyString())).thenReturn(result);

    mockMvc.perform(post("/reissue")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(accessToken)))
        .andExpect(status().isOk())
        .andDo(print());
  }

  @Test
  void tokenReIssuedFailedReLogin() throws Exception {
    String accessToken = "accessToken";
    String reLogin = "다시 로그인 하세요.";
    when(tokenService.tokenReIssued(anyString())).thenReturn(reLogin);

    mockMvc.perform(post("/reissue")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(accessToken)))
        .andExpect(status().isOk())
        .andDo(print());
  }

  @Test
  void tokenReIssuedFailedInvalidToken() throws Exception {
    String accessToken = "accessToken";
    String invalidToken = "유효하지 않은 토큰입니다.";
    when(tokenService.tokenReIssued(anyString())).thenReturn(invalidToken);

    mockMvc.perform(post("/reissue")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(accessToken)))
        .andExpect(status().isOk())
        .andDo(print());
  }

  @Test
  void logout() throws Exception {
    MockHttpServletRequest request = new MockHttpServletRequest();
    request.addHeader("Authorization", "authorization");
    doNothing().when(tokenService).logout(request.getHeader("Authorization"));

    mockMvc.perform(get("/auth/logout"))
        .andExpect(status().isOk())
        .andDo(print());
  }
}