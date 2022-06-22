package cinema.service.controllers;

import cinema.service.models.Screening;
import cinema.service.repositories.ScreeningRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
@ActiveProfiles(profiles = "test")
class ScreeningControllerIntegrationTest {

  @Autowired private MockMvc mvc;
  @Autowired private ScreeningRepository screeningRepository;

  @Test
  void shouldReturnValidDateIsoString() throws Exception {
    // GIVEN
    var s = new Screening();
    s.setBeginning(LocalDateTime.now());
    screeningRepository.save(s);

    // WHEN
    MvcResult mvcResult =
        this.mvc.perform(get("/screenings/last")).andExpect(status().isOk()).andReturn();
    String content = mvcResult.getResponse().getContentAsString();
    String cleanedDateText = content.replaceAll("\"", "");

    // THEN
    assertDoesNotThrow(() -> LocalDate.parse(cleanedDateText));
  }
}
