package fr.ancyracademy.esportclash.modules.schedule.e2e;

import fr.ancyracademy.esportclash.PostgreSQLDbConfiguration;
import fr.ancyracademy.esportclash.modules.player.model.Player;
import fr.ancyracademy.esportclash.modules.player.model.Role;
import fr.ancyracademy.esportclash.modules.player.ports.PlayerRepository;
import fr.ancyracademy.esportclash.modules.schedule.model.Match;
import fr.ancyracademy.esportclash.modules.schedule.model.Moment;
import fr.ancyracademy.esportclash.modules.schedule.model.ScheduleDay;
import fr.ancyracademy.esportclash.modules.schedule.ports.ScheduleDayRepository;
import fr.ancyracademy.esportclash.modules.team.model.Team;
import fr.ancyracademy.esportclash.modules.team.ports.TeamRepository;
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

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@AutoConfigureMockMvc
@Import(PostgreSQLDbConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class CancelMatchE2ETests {
  ScheduleDay scheduleDay;
  Match match;

  @Autowired
  private TeamRepository teamRepository;

  @Autowired
  private PlayerRepository playerRepository;

  @Autowired
  private ScheduleDayRepository scheduleDayRepository;

  @Autowired
  private MockMvc mockMvc;

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

    var skt = createTeam("skt");
    var fnatic = createTeam("fnatic");

    scheduleDay = new ScheduleDay("id", LocalDate.parse("2024-01-01"));
    match = new Match("id", skt, fnatic);

    scheduleDay.schedule(Moment.MORNING, match);

    scheduleDayRepository.save(scheduleDay);
  }


  @Test
  public void shouldCancelMatch() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.delete("/matches/" + match.getId()))
        .andExpect(MockMvcResultMatchers.status().isNoContent());

    var schedule = scheduleDayRepository.findById(scheduleDay.getId());
    assertFalse(schedule.isPresent());
  }
}
