package fr.ancyracademy.esportclash.modules.schedule.model;

import fr.ancyracademy.esportclash.modules.player.model.Role;
import fr.ancyracademy.esportclash.modules.team.model.Team;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ScheduleDayTests {
  private Team createSKT() {
    var team = new Team(
        "skt",
        "SKT"
    );

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
        "Fnatic"
    );

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
        "Damwon"
    );

    team.join("dmw-top", Role.TOP);
    team.join("dmw-jgl", Role.JUNGLE);
    team.join("dmw-mid", Role.MID);
    team.join("dmw-bot", Role.BOTTOM);
    team.join("dmw-supp", Role.SUPPORT);

    return team;
  }

  @Test
  public void whenSchedulingAMatch_shouldBeScheduled() {
    var fnc = createFNC();
    var skt = createSKT();

    var scheduleDay = new ScheduleDay("id", LocalDate.now());

    scheduleDay.schedule(Moment.MORNING, fnc, skt);

    var morningMatch = scheduleDay.getMatch(Moment.MORNING).get();
    assertEquals(morningMatch.getFirst().getId(), fnc.getId());
    assertEquals(morningMatch.getSecond().getId(), skt.getId());
  }

  @Test
  public void whenReschedulingAtAnotherMoment_shouldThrow() {
    var fnc = createFNC();
    var skt = createSKT();

    var scheduleDay = new ScheduleDay("id", LocalDate.now());

    scheduleDay.schedule(Moment.MORNING, fnc, skt);

    var exception = assertThrows(
        IllegalStateException.class,
        () -> scheduleDay.schedule(Moment.AFTERNOON, fnc, skt)
    );

    assertEquals("Team Fnatic is already playing that day", exception.getMessage());
  }

  @Test
  public void whenFirstTeamAlreadyPlaysThatDay_shouldThrow() {
    var fnc = createFNC();
    var skt = createSKT();
    var damwon = createDamwon();

    var scheduleDay = new ScheduleDay("id", LocalDate.now());

    scheduleDay.schedule(Moment.MORNING, fnc, skt);

    var exception = assertThrows(
        IllegalStateException.class,
        () -> scheduleDay.schedule(Moment.AFTERNOON, skt, damwon)
    );

    assertEquals(
        "Team SKT is already playing that day",
        exception.getMessage()
    );
  }

  @Test
  public void whenSecondTeamAlreadyPlaysThatDay_shouldThrow() {
    var fnc = createFNC();
    var skt = createSKT();
    var damwon = createDamwon();

    var scheduleDay = new ScheduleDay("id", LocalDate.now());

    scheduleDay.schedule(Moment.MORNING, fnc, skt);

    var exception = assertThrows(
        IllegalStateException.class,
        () -> scheduleDay.schedule(Moment.AFTERNOON, damwon, skt)
    );

    assertEquals(
        "Team SKT is already playing that day",
        exception.getMessage()
    );
  }


  @Test
  public void whenCancelingScheduledMatch_shouldCancel() {
    var fnc = createFNC();
    var skt = createSKT();

    var scheduleDay = new ScheduleDay("id", LocalDate.now());

    scheduleDay.schedule(Moment.MORNING, fnc, skt);
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
    var fnc = createFNC();
    var skt = createSKT();

    var scheduleDay = new ScheduleDay("id", LocalDate.now());

    scheduleDay.schedule(Moment.MORNING, fnc, skt);

    assertFalse(scheduleDay.isEmpty());
  }
}
