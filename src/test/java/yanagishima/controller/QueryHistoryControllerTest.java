package yanagishima.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import yanagishima.service.QueryService;

@SpringBootTest
@AutoConfigureMockMvc
class QueryHistoryControllerTest {
  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private QueryService queryService;

  @Test
  void getWithoutQueryIdsReturnsEmptyResult() throws Exception {
    mockMvc.perform(get("/queryHistory")
                       .param("datasource", "test"))
           .andDo(print())
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.results").isArray())
           .andExpect(jsonPath("$.results").isEmpty());
  }
}
