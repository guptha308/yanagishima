package yanagishima.controller;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;

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
  private static final String QUERY_HISTORY = "/queryHistory";

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private QueryService queryService;

  @Test
  void getWithoutQueryIds() throws Exception {
    when(queryService.getAll(anyString(), anyList())).thenReturn(Collections.emptyList());

    mockMvc
        .perform(get(QUERY_HISTORY)
                     .param("datasource", "test-datasource"))
        .andDo(print())
        .andExpect(status().isOk());
  }
}
