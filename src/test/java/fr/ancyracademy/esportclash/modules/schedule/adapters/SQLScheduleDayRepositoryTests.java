package fr.ancyracademy.esportclash.modules.schedule.adapters;

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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.testcontainers.containers.PostgreSQLContainer;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Import(PostgreSQLDbConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class SQLScheduleDayRepositoryTests {
  @Autowired
  private ScheduleDayRepository repository;

  @Autowired
  private TeamRepository teamRepository;

  @Autowired
  private PlayerRepository playerRepository;

  @Autowired
  private PostgreSQLContainer container;

  @BeforeEach
  void setUp() {
    repository.clear();
    System.out.println(container.getMappedPort(5432));
  }

  @Test
  void shouldSaveScheduleDay() {
    var fnaticTop = new Player("fnatic-top", "Top", Role.TOP);
    var fnaticJungle = new Player("fnatic-jungle", "Jungle", Role.JUNGLE);
    var fnaticMid = new Player("fnatic-mid", "Mid", Role.MID);
    var fnaticBottom = new Player("fnatic-bottom", "Bottom", Role.BOTTOM);
    var fnaticSupport = new Player("fnatic-support", "Support", Role.SUPPORT);

    var sktTop = new Player("skt-top", "Top", Role.TOP);
    var sktJungle = new Player("skt-jungle", "Jungle", Role.JUNGLE);
    var sktMid = new Player("skt-mid", "Mid", Role.MID);
    var sktBottom = new Player("skt-bottom", "Bottom", Role.BOTTOM);
    var sktSupport = new Player("skt-support", "Support", Role.SUPPORT);

    playerRepository.save(fnaticTop);
    playerRepository.save(fnaticJungle);
    playerRepository.save(fnaticMid);
    playerRepository.save(fnaticBottom);
    playerRepository.save(fnaticSupport);

    playerRepository.save(sktTop);
    playerRepository.save(sktJungle);
    playerRepository.save(sktMid);
    playerRepository.save(sktBottom);
    playerRepository.save(sktSupport);

    var fnatic = new Team("fnatic", "Fnatic");
    fnatic.join(fnaticTop.getId(), Role.TOP);
    fnatic.join(fnaticJungle.getId(), Role.JUNGLE);
    fnatic.join(fnaticMid.getId(), Role.MID);
    fnatic.join(fnaticBottom.getId(), Role.BOTTOM);
    fnatic.join(fnaticSupport.getId(), Role.SUPPORT);

    var skt = new Team("skt", "SKT");
    skt.join(sktTop.getId(), Role.TOP);
    skt.join(sktJungle.getId(), Role.JUNGLE);
    skt.join(sktMid.getId(), Role.MID);
    skt.join(sktBottom.getId(), Role.BOTTOM);
    skt.join(sktSupport.getId(), Role.SUPPORT);

    teamRepository.save(fnatic);
    teamRepository.save(skt);

    var scheduleDay = new ScheduleDay("schedule-day", LocalDate.parse("2024-01-01"));

    var match = new Match("match-1", fnatic, skt);
    scheduleDay.schedule(Moment.MORNING, match);

    repository.save(scheduleDay);

    var savedScheduleDay = repository.findById(scheduleDay.getId()).orElseThrow();

    assertEquals(scheduleDay.getId(), savedScheduleDay.getId());
    assertEquals(scheduleDay.getDate(), savedScheduleDay.getDate());
    assertEquals(scheduleDay.getMatches().size(), savedScheduleDay.getMatches().size());
    assertTrue(savedScheduleDay.containsMatch(match.getId()));

    var savedMatch = savedScheduleDay.getMatch(Moment.MORNING).orElseThrow();
    assertEquals(match.getId(), savedMatch.getId());
    assertEquals(match.getFirst().getId(), savedMatch.getFirst().getId());
    assertEquals(match.getSecond().getId(), savedMatch.getSecond().getId());
  }
}
