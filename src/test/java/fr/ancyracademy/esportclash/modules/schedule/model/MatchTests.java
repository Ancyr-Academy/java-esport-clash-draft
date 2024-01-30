package fr.ancyracademy.esportclash.modules.schedule.model;

import fr.ancyracademy.esportclash.modules.player.model.Role;
import fr.ancyracademy.esportclash.modules.team.model.Team;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class MatchTests {
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

  @Test
  public void whenFirstTeamIsNull_shouldThrow() {
    assertThrows(IllegalArgumentException.class, () -> new Match("id", null, createFNC()));
  }

  @Test
  public void whenSecondTeamIsNull_shouldThrow() {
    assertThrows(IllegalArgumentException.class, () -> new Match("id", createSKT(), null));
  }

  @Test
  public void whenOpposingSameTeam_shouldThrow() {
    var skt = createSKT();
    assertThrows(IllegalStateException.class, () -> new Match("id", skt, skt));
  }

  @Test
  public void whenFirstTeamIsIncomplete_shouldThrow() {
    var skt = createSKT();
    var fnc = createFNC();
    skt.leave("skt-supp");

    assertThrows(IllegalStateException.class, () -> new Match("id", skt, fnc));
  }

  @Test
  public void whenSecondTeamIsIncomplete_shouldThrow() {
    var skt = createSKT();
    var fnc = createFNC();
    fnc.leave("fnc-supp");

    assertThrows(IllegalStateException.class, () -> new Match("id", skt, fnc));
  }
}
