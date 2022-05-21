package cinema.service.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
public class ScreeningControllerTests {

  @Autowired private MockMvc mvc;

  @Test
  public void shouldReturnValidDateIsoString() throws Exception {
    MvcResult mvcResult =
        this.mvc.perform(get("/screenings/last")).andExpect(status().isOk()).andReturn();

    String content = mvcResult.getResponse().getContentAsString();
    String cleanedDateText = content.replaceAll("\"", "");

    assertDoesNotThrow(() -> LocalDate.parse(cleanedDateText));
  }
}
