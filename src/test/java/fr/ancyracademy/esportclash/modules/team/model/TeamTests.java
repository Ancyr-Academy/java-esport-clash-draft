package fr.ancyracademy.esportclash.modules.team.model;

import fr.ancyracademy.esportclash.modules.player.model.Role;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class TeamTests {
  Team createTeamFixture() {
    return new Team(
        UUID.randomUUID(),
        "SKT T1",
        new ArrayList<>());
  }

  @Test
  void whenTeamIsNotFull_shouldBeNotComplete() {
    var team = createTeamFixture();

    team.join(UUID.randomUUID(), Role.TOP);
    team.join(UUID.randomUUID(), Role.JUNGLE);
    team.join(UUID.randomUUID(), Role.MID);
    team.join(UUID.randomUUID(), Role.BOTTOM);

    assertFalse(team.isComplete());
  }

  @Test
  void whenTeamIsFull_shouldBeComplete() {
    var team = createTeamFixture();

    team.join(UUID.randomUUID(), Role.TOP);
    team.join(UUID.randomUUID(), Role.JUNGLE);
    team.join(UUID.randomUUID(), Role.MID);
    team.join(UUID.randomUUID(), Role.BOTTOM);
    team.join(UUID.randomUUID(), Role.SUPPORT);

    assertTrue(team.isComplete());
  }

  @Test
  void whenTeamIsFullButLacksRole_shouldRefuseToAddTwiceTheSameRole() {
    var team = createTeamFixture();

    team.join(UUID.randomUUID(), Role.TOP);
    team.join(UUID.randomUUID(), Role.JUNGLE);
    team.join(UUID.randomUUID(), Role.MID);
    team.join(UUID.randomUUID(), Role.BOTTOM);

    Exception exception = assertThrows(IllegalArgumentException.class, () -> team.join(UUID.randomUUID(), Role.BOTTOM));
    assertEquals("Role already taken", exception.getMessage());
  }

  @Test
  void whenTeamIsFullButLacksRole_shouldRefuseToAddTwiceTheSameUser() {
    var team = createTeamFixture();

    team.join(UUID.fromString("3605348c-6a30-48cd-ba16-3d739abb5880"), Role.TOP);
    team.join(UUID.fromString("3605348c-6a30-48cd-ba16-3d739abb5881"), Role.JUNGLE);
    team.join(UUID.fromString("3605348c-6a30-48cd-ba16-3d739abb5882"), Role.MID);
    team.join(UUID.fromString("3605348c-6a30-48cd-ba16-3d739abb5883"), Role.BOTTOM);


    Exception exception = assertThrows(
        IllegalArgumentException.class,
        () -> team.join(UUID.fromString("3605348c-6a30-48cd-ba16-3d739abb5883"), Role.BOTTOM)
    );

    assertEquals("Player already in team", exception.getMessage());
  }

  @Test
  void whenThePlayerIsInTheTeam_shouldLeaveTheTeam() {
    var team = createTeamFixture();

    var playerId = UUID.randomUUID();
    team.join(playerId, Role.TOP);
    team.leave(playerId);

    assertFalse(team.hasPlayer(playerId));
  }

  @Test
  void whenThePlayerIsNotInTheTeam_shouldThrowAnError() {
    var team = createTeamFixture();

    var playerId = UUID.randomUUID();
    team.join(playerId, Role.TOP);
    team.leave(playerId);

    assertFalse(team.hasPlayer(playerId));
  }
}
