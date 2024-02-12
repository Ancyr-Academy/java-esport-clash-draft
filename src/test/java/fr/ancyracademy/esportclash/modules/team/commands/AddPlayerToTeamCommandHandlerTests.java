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

public class AddPlayerToTeamCommandHandlerTests {
  InMemoryTeamRepository teamRepository = new InMemoryTeamRepository();

  InMemoryPlayerRepository playerRepository = new InMemoryPlayerRepository();

  Player faker = new Player("faker", "Faker", Role.MID);

  Team skt = new Team("skt", "SKT");

  Team damwon = new Team("damwon", "Damwon");

  AddPlayerToTeamCommandHandler createUseCase() {
    return new AddPlayerToTeamCommandHandler(playerRepository, teamRepository);
  }

  @BeforeEach
  void setUp() {
    teamRepository.clear();
    teamRepository.save(skt);
    teamRepository.save(damwon);

    playerRepository.clear();
    playerRepository.save(faker);
  }

  @Nested
  class Scenario_HappyPath {
    AddPlayerToTeamCommand input = new AddPlayerToTeamCommand(
        skt.getId(),
        faker.getId(),
        Role.MID
    );

    @Test
    void shouldAddPlayerToTeam() {
      var useCase = createUseCase();
      useCase.handle(input);

      var team = teamRepository.findById(input.getTeamId()).get();
      assertTrue(team.hasPlayer(input.getPlayerId()));
    }
  }

  @Nested
  class Scenario_TheTeamDoesNotExists {
    AddPlayerToTeamCommand input = new AddPlayerToTeamCommand(
        "random-id",
        faker.getId(),
        Role.MID
    );

    @Test
    void shouldThrow() {
      var useCase = createUseCase();
      var exception = assertThrows(NotFoundException.class, () -> useCase.handle(input));
      assertEquals("Team not found", exception.getMessage());
    }
  }

  @Nested
  class Scenario_ThePlayerDoesNotExists {
    AddPlayerToTeamCommand input = new AddPlayerToTeamCommand(
        skt.getId(),
        "random-id",
        Role.MID
    );

    @Test
    void shouldThrow() {
      var useCase = createUseCase();
      var exception = assertThrows(NotFoundException.class, () -> useCase.handle(input));
      assertEquals("Player not found", exception.getMessage());
    }
  }

  @Nested
  class Scenario_ThePlayerIsAlreadyInAnotherTeam {
    AddPlayerToTeamCommand input = new AddPlayerToTeamCommand(
        damwon.getId(),
        faker.getId(),
        Role.MID
    );

    @BeforeEach
    void setUp() {
      skt.join(faker.getId(), Role.MID);
      teamRepository.save(skt);
    }

    @Test
    void shouldThrow() {
      var useCase = createUseCase();
      var exception = assertThrows(RuntimeException.class, () -> useCase.handle(input));
      assertEquals("Player already in another team", exception.getMessage());
    }
  }
}
