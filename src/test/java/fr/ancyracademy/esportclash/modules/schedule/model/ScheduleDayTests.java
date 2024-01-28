package fr.ancyracademy.esportclash.modules.schedule.model;

import fr.ancyracademy.esportclash.modules.player.model.Role;
import fr.ancyracademy.esportclash.modules.team.model.Team;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

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
    var scheduleDay = new ScheduleDay("id", LocalDate.now());
    var match = new Match("id", createFNC(), createSKT());

    scheduleDay.schedule(Moment.MORNING, match);

    var morningMatch = scheduleDay.getMatch(Moment.MORNING).get();
    assertEquals(match, morningMatch);
  }

  @Test
  public void whenReschedulingAtAnotherMoment_shouldThrow() {
    var scheduleDay = new ScheduleDay("id", LocalDate.now());
    var match = new Match("id", createFNC(), createSKT());

    scheduleDay.schedule(Moment.MORNING, match);

    var exception = assertThrows(
        IllegalStateException.class,
        () -> scheduleDay.schedule(Moment.AFTERNOON, match)
    );

    assertEquals("Match already scheduled", exception.getMessage());
  }

  @Test
  public void whenReschedulingAtSameMoment_shouldSkip() {
    var scheduleDay = new ScheduleDay("id", LocalDate.now());
    var match = new Match("id", createFNC(), createSKT());

    scheduleDay.schedule(Moment.MORNING, match);
    scheduleDay.schedule(Moment.MORNING, match);

    var morningMatch = scheduleDay.getMatch(Moment.MORNING).get();
    assertEquals(match, morningMatch);
  }

  @Test
  public void whenFirstTeamAlreadyPlaysThatDay_shouldThrow() {
    var scheduleDay = new ScheduleDay("id", LocalDate.now());
    var morningMatch = new Match("id", createFNC(), createSKT());
    var afternoonMatch = new Match("id", createSKT(), createDamwon());

    scheduleDay.schedule(Moment.MORNING, morningMatch);

    var exception = assertThrows(
        IllegalStateException.class,
        () -> scheduleDay.schedule(Moment.AFTERNOON, afternoonMatch)
    );

    assertEquals(
        "Team SKT is already playing that day",
        exception.getMessage()
    );
  }

  @Test
  public void whenSecondTeamAlreadyPlaysThatDay_shouldThrow() {
    var scheduleDay = new ScheduleDay("id", LocalDate.now());
    var morningMatch = new Match("id", createFNC(), createSKT());
    var afternoonMatch = new Match("id", createDamwon(), createSKT());

    scheduleDay.schedule(Moment.MORNING, morningMatch);

    var exception = assertThrows(
        IllegalStateException.class,
        () -> scheduleDay.schedule(Moment.AFTERNOON, afternoonMatch)
    );

    assertEquals(
        "Team SKT is already playing that day",
        exception.getMessage()
    );
  }

  @Test
  public void whenAddingMatch_shouldContainMatch() {
    var scheduleDay = new ScheduleDay("id", LocalDate.now());
    var morningMatch = new Match("morning-match", createFNC(), createSKT());

    scheduleDay.schedule(Moment.MORNING, morningMatch);

    assertTrue(scheduleDay.containsMatch("morning-match"));
  }

  @Test
  public void whenCancelingScheduledMatch_shouldCancel() {
    var scheduleDay = new ScheduleDay("id", LocalDate.now());
    var morningMatch = new Match("morning-match", createFNC(), createSKT());

    scheduleDay.schedule(Moment.MORNING, morningMatch);
    scheduleDay.cancel("morning-match");

    assertFalse(scheduleDay.containsMatch("morning-match"));
  }

  @Test
  public void whenScheduleIsEmpty_shouldReturnTrue() {
    var scheduleDay = new ScheduleDay("id", LocalDate.now());
    assertTrue(scheduleDay.isEmpty());
  }

  @Test
  public void whenScheduleIsNotEmpty_shouldReturnFalse() {
    var scheduleDay = new ScheduleDay("id", LocalDate.now());
    var morningMatch = new Match("morning-match", createFNC(), createSKT());

    scheduleDay.schedule(Moment.MORNING, morningMatch);

    assertFalse(scheduleDay.isEmpty());
  }
}
