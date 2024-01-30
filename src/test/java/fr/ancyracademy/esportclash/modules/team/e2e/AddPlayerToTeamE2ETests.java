package fr.ancyracademy.esportclash.modules.team.e2e;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.ancyracademy.esportclash.PostgreSQLDbConfiguration;
import fr.ancyracademy.esportclash.modules.player.model.Player;
import fr.ancyracademy.esportclash.modules.player.model.Role;
import fr.ancyracademy.esportclash.modules.player.ports.PlayerRepository;
import fr.ancyracademy.esportclash.modules.team.model.Team;
import fr.ancyracademy.esportclash.modules.team.ports.TeamRepository;
import fr.ancyracademy.esportclash.modules.team.spring.dto.AddPlayerToTeamDTO;
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

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureMockMvc
@Import(PostgreSQLDbConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class AddPlayerToTeamE2ETests {
  Team skt = new Team("skt", "SKT");

  Player faker = new Player("faker", "Faker", Role.MID);

  @Autowired
  private TeamRepository teamRepository;

  @Autowired
  private PlayerRepository playerRepository;

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @BeforeEach
  void setUp() {
    teamRepository.clear();
    playerRepository.clear();

    teamRepository.save(skt);
    playerRepository.save(faker);
  }

  @Test
  public void shouldAddPlayerToTeam() throws Exception {
    var dto = new AddPlayerToTeamDTO();
    dto.setPlayerId(faker.getId());
    dto.setRole(Role.MID.toString());

    mockMvc
        .perform(MockMvcRequestBuilders.post("/teams/" + skt.getId() + "/players")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(dto)))
        .andExpect(MockMvcResultMatchers.status().isOk());

    var team = teamRepository.findById(skt.getId()).orElseThrow();
    assertTrue(team.hasPlayer(faker.getId()));
  }
}
