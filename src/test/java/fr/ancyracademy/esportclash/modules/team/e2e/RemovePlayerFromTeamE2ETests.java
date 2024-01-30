package fr.ancyracademy.esportclash.modules.team.e2e;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.ancyracademy.esportclash.PostgreSQLDbConfiguration;
import fr.ancyracademy.esportclash.modules.player.model.Player;
import fr.ancyracademy.esportclash.modules.player.model.Role;
import fr.ancyracademy.esportclash.modules.player.ports.PlayerRepository;
import fr.ancyracademy.esportclash.modules.team.model.Team;
import fr.ancyracademy.esportclash.modules.team.ports.TeamRepository;
import fr.ancyracademy.esportclash.modules.team.spring.dto.RemovePlayerFromTeamDTO;
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

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@AutoConfigureMockMvc
@Import(PostgreSQLDbConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class RemovePlayerFromTeamE2ETests {
  Team skt;

  Player faker;

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
    playerRepository.clear();
    teamRepository.clear();

    faker = new Player("faker", "Faker", Role.MID);

    skt = new Team("skt", "SKT");
    skt.join(faker.getId(), faker.getMainRole());

    playerRepository.save(faker);
    teamRepository.save(skt);
  }

  @Test
  public void shouldRemovePlayerFromTeam() throws Exception {
    var dto = new RemovePlayerFromTeamDTO();
    dto.setPlayerId(faker.getId());

    mockMvc
        .perform(MockMvcRequestBuilders.delete("/teams/" + skt.getId() + "/players/" + faker.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(dto)))
        .andExpect(MockMvcResultMatchers.status().isNoContent());

    var team = teamRepository.findById(skt.getId()).orElseThrow();
    assertFalse(team.hasPlayer(faker.getId()));
  }
}
