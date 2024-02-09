package fr.ancyracademy.esportclash.modules.schedule.e2e;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.ancyracademy.esportclash.PostgreSQLDbConfiguration;
import fr.ancyracademy.esportclash.modules.player.model.Player;
import fr.ancyracademy.esportclash.modules.player.model.Role;
import fr.ancyracademy.esportclash.modules.player.ports.PlayerRepository;
import fr.ancyracademy.esportclash.modules.schedule.model.Moment;
import fr.ancyracademy.esportclash.modules.schedule.ports.ScheduleDayRepository;
import fr.ancyracademy.esportclash.modules.schedule.spring.dto.OrganizeMatchDTO;
import fr.ancyracademy.esportclash.modules.team.model.Team;
import fr.ancyracademy.esportclash.modules.team.ports.TeamRepository;
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
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureMockMvc
@Import(PostgreSQLDbConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class OrganizeMatchE2ETests {
  Team skt;
  Team fnatic;


  @Autowired
  private TeamRepository teamRepository;

  @Autowired
  private PlayerRepository playerRepository;

  @Autowired
  private ScheduleDayRepository scheduleDayRepository;

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  Team createTeam(String name) {
    var team = new Team(name, name);

    var top = new Player(name + "-top", name + "-top", Role.TOP);
    var jungle = new Player(name + "-jungle", name + "-jungle", Role.JUNGLE);
    var mid = new Player(name + "-mid", name + "-mid", Role.MID);
    var bottom = new Player(name + "-bottom", name + "-bottom", Role.BOTTOM);
    var support = new Player(name + "-support", name + "-support", Role.SUPPORT);

    playerRepository.save(top);
    playerRepository.save(jungle);
    playerRepository.save(mid);
    playerRepository.save(bottom);
    playerRepository.save(support);

    team.join(top.getId(), Role.TOP);
    team.join(jungle.getId(), Role.JUNGLE);
    team.join(mid.getId(), Role.MID);
    team.join(bottom.getId(), Role.BOTTOM);
    team.join(support.getId(), Role.SUPPORT);

    teamRepository.save(team);

    return team;
  }

  @BeforeEach
  void setUp() {
    scheduleDayRepository.clear();
    teamRepository.clear();
    playerRepository.clear();

    skt = createTeam("skt");
    fnatic = createTeam("fnatic");
  }


  @Test
  public void shouldOrganizeAMatch() throws Exception {
    var dto = new OrganizeMatchDTO();
    dto.setDate("2024-01-01");
    dto.setFirstTeamId(skt.getId());
    dto.setSecondTeamId(fnatic.getId());
    dto.setMoment(Moment.MORNING);

    var result = mockMvc
        .perform(MockMvcRequestBuilders.post("/schedule/organize")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(dto)))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn().getResponse().getContentAsString();

    var response = objectMapper.readValue(result, IdResponse.class);

    var schedule = scheduleDayRepository.findById(response.getId()).orElseThrow();
    assertEquals(dto.getDate(), schedule.getDay());

    var match = schedule.getMatch(Moment.MORNING).orElseThrow();

    assertTrue(match.includesTeam(skt));
    assertTrue(match.includesTeam(fnatic));
  }
}
