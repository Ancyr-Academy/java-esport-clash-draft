package fr.ancyracademy.esportclash.modules.schedule.commands;

import fr.ancyracademy.esportclash.modules.player.model.Role;
import fr.ancyracademy.esportclash.modules.schedule.adapters.ram.InMemoryScheduleDayRepository;
import fr.ancyracademy.esportclash.modules.schedule.model.Moment;
import fr.ancyracademy.esportclash.modules.schedule.model.ScheduleDay;
import fr.ancyracademy.esportclash.modules.schedule.ports.ScheduleDayRepository;
import fr.ancyracademy.esportclash.modules.team.adapters.ram.InMemoryTeamRepository;
import fr.ancyracademy.esportclash.modules.team.model.Team;
import fr.ancyracademy.esportclash.modules.team.ports.TeamRepository;
import fr.ancyracademy.esportclash.shared.exceptions.NotFoundException;
import fr.ancyracademy.esportclash.shared.id.FixedIdProvider;
import fr.ancyracademy.esportclash.shared.id.IdProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class OrganizeMatchCommandHandlerTests {
  IdProvider idProvider = new FixedIdProvider();
  ScheduleDayRepository scheduleDayRepository = new InMemoryScheduleDayRepository();
  TeamRepository teamRepository = new InMemoryTeamRepository();

  Team skt;
  Team fnatic;
  Team damwon;
  Team g2;

  ScheduleDay scheduleDay;

  @BeforeEach
  void setUp() {
    scheduleDayRepository.clear();
    teamRepository.clear();

    skt = createTeam("skt");
    fnatic = createTeam("fnatic");
    damwon = createTeam("damwon");
    g2 = createTeam("g2");

    teamRepository.save(skt);
    teamRepository.save(fnatic);
    teamRepository.save(damwon);
    teamRepository.save(g2);

    scheduleDay = new ScheduleDay(idProvider.generate(), LocalDate.parse("2024-01-01"));
    scheduleDay.schedule(Moment.AFTERNOON, damwon, g2);

    scheduleDayRepository.save(scheduleDay);
  }

  Team createTeam(String name) {
    var team = new Team(name, name);
    team.join(name + "-top", Role.TOP);
    team.join(name + "-jungle", Role.JUNGLE);
    team.join(name + "-mid", Role.MID);
    team.join(name + "-bottom", Role.BOTTOM);
    team.join(name + "-support", Role.SUPPORT);
    return team;
  }

  OrganizeMatchCommandHandler createUseCase() {
    return new OrganizeMatchCommandHandler(
        idProvider,
        scheduleDayRepository,
        teamRepository
    );
  }

  void assertMatchIsScheduled(ScheduleDay day, Moment moment, Team firstTeam, Team secondTeam) {
    var matchQuery = day.getMatch(moment);
    assertTrue(matchQuery.isPresent());

    var match = matchQuery.get();
    assertTrue(match.includesTeam(firstTeam));
    assertTrue(match.includesTeam(secondTeam));
  }

  @Nested
  class Scenario_HappyPath {
    OrganizeMatchCommand input = new OrganizeMatchCommand(
        LocalDate.parse("2024-01-02"),
        "skt",
        "fnatic",
        Moment.MORNING
    );

    @Test
    void shouldReturnTheId() {
      var useCase = createUseCase();
      var result = useCase.handle(input);

      assertEquals(idProvider.generate(), result.getId());
    }

    @Test
    void shouldStoreTheMatch() {
      var useCase = createUseCase();
      useCase.handle(input);

      var scheduleDay = scheduleDayRepository.findByDate(input.getDate()).orElseThrow();
      assertMatchIsScheduled(scheduleDay, Moment.MORNING, skt, fnatic);
    }
  }

  @Nested
  class Scenario_FirstTeamDoesNotExist {
    OrganizeMatchCommand input = new OrganizeMatchCommand(
        LocalDate.parse("2024-01-02"),
        "random",
        "fnatic",
        Moment.MORNING
    );


    @Test
    void shouldThrowNotFound() {
      var useCase = createUseCase();
      var exception = assertThrows(NotFoundException.class, () -> useCase.handle(input));
      assertEquals("Team not found", exception.getMessage());
    }
  }

  @Nested
  class Scenario_SecondTeamDoesNotExist {

    OrganizeMatchCommand input = new OrganizeMatchCommand(
        LocalDate.parse("2024-01-02"),
        "skt",
        "random",
        Moment.MORNING
    );

    @Test
    void shouldThrowNotFound() {
      var useCase = createUseCase();
      var exception = assertThrows(NotFoundException.class, () -> useCase.handle(input));
      assertEquals("Team not found", exception.getMessage());
    }
  }

  @Nested
  class Scenario_MatchInTheMorningOfTheSameDay {

    OrganizeMatchCommand input = new OrganizeMatchCommand(
        LocalDate.parse("2024-01-01"),
        "skt",
        "fnatic",
        Moment.MORNING
    );

    @Test
    void shouldReturnTheId() {
      var useCase = createUseCase();
      var result = useCase.handle(input);
      assertEquals(idProvider.generate(), result.getId());
    }

    @Test
    void shouldStoreTheMorningMatchAndKeepAfternoonMatch() {
      var useCase = createUseCase();
      useCase.handle(input);

      var scheduleDay = scheduleDayRepository.findByDate(input.getDate()).orElseThrow();

      assertMatchIsScheduled(scheduleDay, Moment.MORNING, skt, fnatic);
      assertMatchIsScheduled(scheduleDay, Moment.AFTERNOON, damwon, g2);
    }
  }
}
