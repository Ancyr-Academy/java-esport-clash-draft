package fr.ancyracademy.esportclash.modules.schedule.usecases;

import fr.ancyracademy.esportclash.modules.player.model.Role;
import fr.ancyracademy.esportclash.modules.schedule.adapters.ram.InMemoryScheduleDayRepository;
import fr.ancyracademy.esportclash.modules.schedule.model.Match;
import fr.ancyracademy.esportclash.modules.schedule.model.Moment;
import fr.ancyracademy.esportclash.modules.schedule.model.ScheduleDay;
import fr.ancyracademy.esportclash.modules.schedule.ports.ScheduleDayRepository;
import fr.ancyracademy.esportclash.modules.team.adapters.ram.InMemoryTeamRepository;
import fr.ancyracademy.esportclash.modules.team.model.Team;
import fr.ancyracademy.esportclash.modules.team.ports.TeamRepository;
import fr.ancyracademy.esportclash.shared.exceptions.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class CancelMatchUseCaseTests {
  ScheduleDayRepository scheduleDayRepository = new InMemoryScheduleDayRepository();
  TeamRepository teamRepository = new InMemoryTeamRepository();

  Team skt;
  Team fnatic;
  Team damwon;
  Team g2;

  ScheduleDay scheduleDay;

  Match damwonVsG2;
  Match sktVsFnatic;

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

    scheduleDay = new ScheduleDay("day-1", LocalDate.parse("2024-01-01"));

    scheduleDay.schedule(Moment.MORNING, skt, fnatic);
    scheduleDay.schedule(Moment.AFTERNOON, damwon, g2);

    sktVsFnatic = scheduleDay.getMatch(Moment.MORNING).get();
    damwonVsG2 = scheduleDay.getMatch(Moment.AFTERNOON).get();

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

  CancelMatchUseCase createUseCase() {
    return new CancelMatchUseCase(
        scheduleDayRepository
    );
  }


  @Nested
  class Scenario_HappyPath {


    @Test
    void shouldCancelTheMatch() {
      CancelMatchInput input = new CancelMatchInput(damwonVsG2.getId());

      var useCase = createUseCase();
      useCase.execute(input);

      var savedScheduleDay = scheduleDayRepository.findById(scheduleDay.getId()).get();
      assertFalse(savedScheduleDay.containsMatch("damwon-g2"));
    }
  }

  @Nested
  class Scenario_NoMatchesRemainingInScheduleDay {
    @BeforeEach
    void setUp() {
      scheduleDay.cancel(sktVsFnatic.getId());
      scheduleDayRepository.save(scheduleDay);
    }

    @Test
    void shouldCancelTheMatch() {
      CancelMatchInput input = new CancelMatchInput(damwonVsG2.getId());

      var useCase = createUseCase();
      useCase.execute(input);

      var savedScheduleDay = scheduleDayRepository.findById(scheduleDay.getId());
      assertFalse(savedScheduleDay.isPresent());
    }
  }

  @Nested
  class Scenario_MatchDoesNotExist {
    CancelMatchInput input = new CancelMatchInput("skt-g2");

    @Test
    void shouldThrow() {
      var useCase = createUseCase();
      var exception = assertThrows(
          NotFoundException.class,
          () -> useCase.execute(input)
      );

      assertEquals(
          "Match with id skt-g2 not found",
          exception.getMessage()
      );
    }
  }

}
