package fr.ancyracademy.esportclash.modules.team.usecases;

import fr.ancyracademy.esportclash.modules.player.adapters.ram.InMemoryPlayerRepository;
import fr.ancyracademy.esportclash.modules.player.model.Player;
import fr.ancyracademy.esportclash.modules.player.model.Role;
import fr.ancyracademy.esportclash.modules.team.adapters.ram.InMemoryTeamRepository;
import fr.ancyracademy.esportclash.modules.team.model.Team;
import fr.ancyracademy.esportclash.modules.team.usescases.AddPlayerInput;
import fr.ancyracademy.esportclash.modules.team.usescases.AddPlayerUseCase;
import fr.ancyracademy.esportclash.shared.exceptions.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class AddPlayerUseCaseTests {
  InMemoryTeamRepository teamRepository = new InMemoryTeamRepository();

  InMemoryPlayerRepository playerRepository = new InMemoryPlayerRepository();

  Player faker = new Player(UUID.randomUUID(), "Faker", Role.MID);

  Team skt = new Team(UUID.randomUUID(), "SKT", new ArrayList<>());

  Team damwon = new Team(UUID.randomUUID(), "Damwon", new ArrayList<>());

  AddPlayerUseCase createUseCase() {
    return new AddPlayerUseCase(playerRepository, teamRepository);
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
    AddPlayerInput input = new AddPlayerInput(
        skt.getId(),
        faker.getId(),
        Role.MID
    );

    @Test
    void shouldAddPlayerToTeam() {
      var useCase = createUseCase();
      useCase.execute(input);

      var team = teamRepository.findById(input.getTeamId()).get();
      assertTrue(team.hasPlayer(input.getPlayerId()));
    }
  }

  @Nested
  class Scenario_TheTeamDoesNotExists {
    AddPlayerInput input = new AddPlayerInput(
        UUID.fromString("00000000-0000-0000-0000-000000000000"),
        faker.getId(),
        Role.MID
    );

    @Test
    void shouldThrow() {
      var useCase = createUseCase();
      var exception = assertThrows(NotFoundException.class, () -> useCase.execute(input));
      assertEquals("Team not found", exception.getMessage());
    }
  }

  @Nested
  class Scenario_ThePlayerDoesNotExists {
    AddPlayerInput input = new AddPlayerInput(
        skt.getId(),
        UUID.fromString("00000000-0000-0000-0000-000000000000"),
        Role.MID
    );

    @Test
    void shouldThrow() {
      var useCase = createUseCase();
      var exception = assertThrows(NotFoundException.class, () -> useCase.execute(input));
      assertEquals("Player not found", exception.getMessage());
    }
  }

  @Nested
  class Scenario_ThePlayerIsAlreadyInAnotherTeam {
    AddPlayerInput input = new AddPlayerInput(
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
      var exception = assertThrows(RuntimeException.class, () -> useCase.execute(input));
      assertEquals("Player already in another team", exception.getMessage());
    }
  }
}
