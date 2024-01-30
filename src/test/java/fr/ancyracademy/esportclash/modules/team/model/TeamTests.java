package fr.ancyracademy.esportclash.modules.team.model;

import fr.ancyracademy.esportclash.modules.player.model.Role;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TeamTests {
  Team createTeamFixture() {
    return new Team(
        "skt",
        "SKT T1"
    );
  }

  @Test
  void whenTeamIsNotFull_shouldBeNotComplete() {
    var team = createTeamFixture();

    team.join("top", Role.TOP);
    team.join("jgl", Role.JUNGLE);
    team.join("mid", Role.MID);
    team.join("bot", Role.BOTTOM);

    assertFalse(team.isComplete());
  }

  @Test
  void whenTeamIsFull_shouldBeComplete() {
    var team = createTeamFixture();

    team.join("top", Role.TOP);
    team.join("jgl", Role.JUNGLE);
    team.join("mid", Role.MID);
    team.join("bot", Role.BOTTOM);
    team.join("supp", Role.SUPPORT);

    assertTrue(team.isComplete());
  }

  @Test
  void whenTeamIsFullButLacksRole_shouldRefuseToAddTwiceTheSameRole() {
    var team = createTeamFixture();

    team.join("top", Role.TOP);
    team.join("jgl", Role.JUNGLE);
    team.join("mid", Role.MID);
    team.join("bot", Role.BOTTOM);

    Exception exception = assertThrows(IllegalArgumentException.class, () -> team.join("bot-2", Role.BOTTOM));
    assertEquals("Role already taken", exception.getMessage());
  }

  @Test
  void whenTeamIsFullButLacksRole_shouldRefuseToAddTwiceTheSameUser() {
    var team = createTeamFixture();

    team.join("top", Role.TOP);
    team.join("jgl", Role.JUNGLE);
    team.join("mid", Role.MID);
    team.join("bot", Role.BOTTOM);


    Exception exception = assertThrows(
        IllegalArgumentException.class,
        () -> team.join("bot", Role.BOTTOM)
    );

    assertEquals("Player already in team", exception.getMessage());
  }

  @Test
  void whenThePlayerIsInTheTeam_shouldLeaveTheTeam() {
    var team = createTeamFixture();

    var playerId = "top-id";
    team.join(playerId, Role.TOP);
    team.leave(playerId);

    assertFalse(team.hasPlayer(playerId));
  }

  @Test
  void whenThePlayerIsNotInTheTeam_shouldThrowAnError() {
    var team = createTeamFixture();

    var playerId = "top-id";
    team.join(playerId, Role.TOP);
    team.leave(playerId);

    assertFalse(team.hasPlayer(playerId));
  }
}
