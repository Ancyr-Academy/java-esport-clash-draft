package fr.ancyracademy.esportclash.modules.team.usecases;

import fr.ancyracademy.esportclash.modules.player.adapters.ram.InMemoryPlayerRepository;
import fr.ancyracademy.esportclash.modules.player.model.Player;
import fr.ancyracademy.esportclash.modules.player.model.Role;
import fr.ancyracademy.esportclash.modules.team.adapters.ram.InMemoryTeamRepository;
import fr.ancyracademy.esportclash.modules.team.model.Team;
import fr.ancyracademy.esportclash.shared.exceptions.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class RemovePlayerFromTeamUseCaseTests {
  InMemoryTeamRepository teamRepository = new InMemoryTeamRepository();

  InMemoryPlayerRepository playerRepository = new InMemoryPlayerRepository();

  Player faker;

  Player teddy;

  Team skt;

  RemovePlayerFromTeamUseCase createUseCase() {
    return new RemovePlayerFromTeamUseCase(playerRepository, teamRepository);
  }

  @BeforeEach
  void setUp() {
    faker = new Player(UUID.randomUUID(), "Faker", Role.MID);
    teddy = new Player(UUID.randomUUID(), "Teddy", Role.BOTTOM);
    skt = new Team(UUID.randomUUID(), "SKT", new ArrayList<>());
    skt.join(faker.getId(), Role.MID);

    teamRepository.clear();
    teamRepository.save(skt);

    playerRepository.clear();
    playerRepository.save(faker);
    playerRepository.save(teddy);
  }

  @Nested
  class Scenario_HappyPath {
    RemovePlayerFromTeamInput createInput() {
      return new RemovePlayerFromTeamInput(
          faker.getId()
      );
    }

    @Test
    void shouldRemovePlayerFromTeam() {
      var input = createInput();
      var useCase = createUseCase();
      useCase.execute(input);

      var team = teamRepository.findById(skt.getId()).get();
      assertFalse(team.hasPlayer(input.playerId()));
    }
  }

  @Nested
  class Scenario_PlayerDoesNotExist {
    RemovePlayerFromTeamInput createInput() {
      return new RemovePlayerFromTeamInput(
          UUID.randomUUID()
      );
    }

    @Test
    void shouldThrowException() {
      var input = createInput();
      var useCase = createUseCase();

      var exception = assertThrows(NotFoundException.class, () -> useCase.execute(input));
      assertEquals("Player not found", exception.getMessage());
    }
  }

  @Nested
  class Scenario_UserHasNoTeam {
    RemovePlayerFromTeamInput createInput() {
      return new RemovePlayerFromTeamInput(
          teddy.getId()
      );
    }

    @Test
    void shouldThrowException() {
      var input = createInput();
      var useCase = createUseCase();

      var exception = assertThrows(NotFoundException.class, () -> useCase.execute(input));
      assertEquals("This player is not in a team", exception.getMessage());
    }
  }
}
