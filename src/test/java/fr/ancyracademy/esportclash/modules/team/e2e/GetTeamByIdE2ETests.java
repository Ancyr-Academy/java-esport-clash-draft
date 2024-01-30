package fr.ancyracademy.esportclash.modules.team.e2e;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.ancyracademy.esportclash.PostgreSQLDbConfiguration;
import fr.ancyracademy.esportclash.modules.player.model.Player;
import fr.ancyracademy.esportclash.modules.player.model.Role;
import fr.ancyracademy.esportclash.modules.player.ports.PlayerRepository;
import fr.ancyracademy.esportclash.modules.team.model.Team;
import fr.ancyracademy.esportclash.modules.team.ports.TeamRepository;
import fr.ancyracademy.esportclash.modules.team.viewmodel.TeamViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
@Import(PostgreSQLDbConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class GetTeamByIdE2ETests {
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
  public void shouldGetTeam() throws Exception {
    var result = mockMvc
        .perform(MockMvcRequestBuilders.get("/teams/" + skt.getId()))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn()
        .getResponse()
        .getContentAsString();

    var teamViewModel = objectMapper.readValue(result, TeamViewModel.class);

    assertEquals(skt.getId(), teamViewModel.id());
    assertEquals(skt.getName(), teamViewModel.name());

    var member = teamViewModel.members().get(0);
    assertEquals(faker.getId(), member.id());
    assertEquals(faker.getName(), member.name());
    assertEquals(Role.MID.toString(), member.role());
  }
}
