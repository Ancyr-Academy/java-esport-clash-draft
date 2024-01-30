package fr.ancyracademy.esportclash.modules.team.e2e;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.ancyracademy.esportclash.PostgreSQLDbConfiguration;
import fr.ancyracademy.esportclash.modules.team.ports.TeamRepository;
import fr.ancyracademy.esportclash.modules.team.spring.dto.CreateTeamDTO;
import fr.ancyracademy.esportclash.shared.IdResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
@Import(PostgreSQLDbConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class CreateTeamE2ETests {
  @Autowired
  private TeamRepository teamRepository;

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @BeforeEach
  void setUp() {
    teamRepository.clear();
  }

  @Test
  public void shouldCreateTeam() throws Exception {
    var dto = new CreateTeamDTO();
    dto.setName("SKT");

    var result = mockMvc
        .perform(MockMvcRequestBuilders.post("/teams")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(dto)))
        .andExpect(MockMvcResultMatchers.status().isCreated())
        .andReturn().getResponse().getContentAsString();

    var response = objectMapper.readValue(result, IdResponse.class);

    var createdTeam = teamRepository.findById(response.getId()).orElseThrow();
    assertEquals(dto.getName(), createdTeam.getName());
  }
}
