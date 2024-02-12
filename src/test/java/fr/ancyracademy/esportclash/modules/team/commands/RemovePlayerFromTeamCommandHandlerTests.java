package fr.ancyracademy.esportclash.modules.team.commands;

import fr.ancyracademy.esportclash.modules.player.adapters.ram.InMemoryPlayerRepository;
import fr.ancyracademy.esportclash.modules.player.model.Player;
import fr.ancyracademy.esportclash.modules.player.model.Role;
import fr.ancyracademy.esportclash.modules.team.adapters.ram.InMemoryTeamRepository;
import fr.ancyracademy.esportclash.modules.team.model.Team;
import fr.ancyracademy.esportclash.shared.exceptions.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RemovePlayerFromTeamCommandHandlerTests {
  InMemoryTeamRepository teamRepository = new InMemoryTeamRepository();

  InMemoryPlayerRepository playerRepository = new InMemoryPlayerRepository();

  Player faker;

  Player teddy;

  Team skt;

  RemovePlayerFromTeamCommandHandler createUseCase() {
    return new RemovePlayerFromTeamCommandHandler(playerRepository, teamRepository);
  }

  @BeforeEach
  void setUp() {
    faker = new Player("faker", "Faker", Role.MID);
    teddy = new Player("gumayusi", "Gumayusi", Role.BOTTOM);
    skt = new Team("skt", "SKT");
    skt.join(faker.getId(), Role.MID);

    teamRepository.clear();
    teamRepository.save(skt);

    playerRepository.clear();
    playerRepository.save(faker);
    playerRepository.save(teddy);
  }

  @Nested
  class Scenario_HappyPath {
    RemovePlayerFromTeamCommand createInput() {
      return new RemovePlayerFromTeamCommand(
          faker.getId()
      );
    }

    @Test
    void shouldRemovePlayerFromTeam() {
      var input = createInput();
      var useCase = createUseCase();
      useCase.handle(input);

      var team = teamRepository.findById(skt.getId()).get();
      assertFalse(team.hasPlayer(input.getPlayerId()));
    }
  }

  @Nested
  class Scenario_PlayerDoesNotExist {
    RemovePlayerFromTeamCommand createInput() {
      return new RemovePlayerFromTeamCommand("random-id");
    }

    @Test
    void shouldThrowException() {
      var input = createInput();
      var useCase = createUseCase();

      var exception = assertThrows(NotFoundException.class, () -> useCase.handle(input));
      assertEquals("Player not found", exception.getMessage());
    }
  }

  @Nested
  class Scenario_UserHasNoTeam {
    RemovePlayerFromTeamCommand createInput() {
      return new RemovePlayerFromTeamCommand(
          teddy.getId()
      );
    }

    @Test
    void shouldThrowException() {
      var input = createInput();
      var useCase = createUseCase();

      var exception = assertThrows(NotFoundException.class, () -> useCase.handle(input));
      assertEquals("This player is not in a team", exception.getMessage());
    }
  }
}
