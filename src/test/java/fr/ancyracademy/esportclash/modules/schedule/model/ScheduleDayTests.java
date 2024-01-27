package fr.ancyracademy.esportclash.modules.schedule.model;

import fr.ancyracademy.esportclash.modules.player.model.Role;
import fr.ancyracademy.esportclash.modules.team.model.Team;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ScheduleDayTests {
  private Team createSKT() {
    var team = new Team(
        "skt",
        "SKT",
        new ArrayList<>());

    team.join("skt-top", Role.TOP);
    team.join("skt-jgl", Role.JUNGLE);
    team.join("skt-mid", Role.MID);
    team.join("skt-bot", Role.BOTTOM);
    team.join("skt-supp", Role.SUPPORT);

    return team;
  }

  private Team createFNC() {
    var team = new Team(
        "fnc",
        "Fnatic",
        new ArrayList<>());

    team.join("fnc-top", Role.TOP);
    team.join("fnc-jgl", Role.JUNGLE);
    team.join("fnc-mid", Role.MID);
    team.join("fnc-bot", Role.BOTTOM);
    team.join("fnc-supp", Role.SUPPORT);

    return team;
  }

  private Team createDamwon() {
    var team = new Team(
        "dmw",
        "Damwon",
        new ArrayList<>());

    team.join("dmw-top", Role.TOP);
    team.join("dmw-jgl", Role.JUNGLE);
    team.join("dmw-mid", Role.MID);
    team.join("dmw-bot", Role.BOTTOM);
    team.join("dmw-supp", Role.SUPPORT);

    return team;
  }

  @Test
  public void whenSchedulingAMatch_shouldBeScheduled() {
    var scheduleDay = new ScheduleDay(LocalDate.now());
    var match = new Match(createFNC(), createSKT());

    scheduleDay.schedule(ScheduleDay.Moment.MORNING, match);

    var morningMatch = scheduleDay.getMatch(ScheduleDay.Moment.MORNING).get();
    assertEquals(match, morningMatch);
  }

  @Test
  public void whenReschedulingAtAnotherMoment_shouldThrow() {
    var scheduleDay = new ScheduleDay(LocalDate.now());
    var match = new Match(createFNC(), createSKT());

    scheduleDay.schedule(ScheduleDay.Moment.MORNING, match);

    var exception = assertThrows(
        IllegalStateException.class,
        () -> scheduleDay.schedule(ScheduleDay.Moment.AFTERNOON, match)
    );

    assertEquals("Match already scheduled", exception.getMessage());
  }

  @Test
  public void whenReschedulingAtSameMoment_shouldSkip() {
    var scheduleDay = new ScheduleDay(LocalDate.now());
    var match = new Match(createFNC(), createSKT());

    scheduleDay.schedule(ScheduleDay.Moment.MORNING, match);
    scheduleDay.schedule(ScheduleDay.Moment.MORNING, match);

    var morningMatch = scheduleDay.getMatch(ScheduleDay.Moment.MORNING).get();
    assertEquals(match, morningMatch);
  }

  @Test
  public void whenFirstTeamAlreadyPlaysThatDay_shouldThrow() {
    var scheduleDay = new ScheduleDay(LocalDate.now());
    var morningMatch = new Match(createFNC(), createSKT());
    var afternoonMatch = new Match(createSKT(), createDamwon());

    scheduleDay.schedule(ScheduleDay.Moment.MORNING, morningMatch);

    var exception = assertThrows(
        IllegalStateException.class,
        () -> scheduleDay.schedule(ScheduleDay.Moment.AFTERNOON, afternoonMatch)
    );

    assertEquals(
        "Team SKT is already playing that day",
        exception.getMessage()
    );
  }

  @Test
  public void whenSecondTeamAlreadyPlaysThatDay_shouldThrow() {
    var scheduleDay = new ScheduleDay(LocalDate.now());
    var morningMatch = new Match(createFNC(), createSKT());
    var afternoonMatch = new Match(createDamwon(), createSKT());

    scheduleDay.schedule(ScheduleDay.Moment.MORNING, morningMatch);

    var exception = assertThrows(
        IllegalStateException.class,
        () -> scheduleDay.schedule(ScheduleDay.Moment.AFTERNOON, afternoonMatch)
    );

    assertEquals(
        "Team SKT is already playing that day",
        exception.getMessage()
    );
  }
}
