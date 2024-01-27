package fr.ancyracademy.esportclash.modules.player.usecases;

import fr.ancyracademy.esportclash.modules.player.adapters.ram.InMemoryPlayerRepository;
import fr.ancyracademy.esportclash.modules.player.model.Player;
import fr.ancyracademy.esportclash.modules.player.model.Role;
import fr.ancyracademy.esportclash.shared.exceptions.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ChangePlayerMainRoleUseCaseTests {
  InMemoryPlayerRepository playerRepository = new InMemoryPlayerRepository();

  Player faker = new Player(UUID.randomUUID(), "Faker", Role.MID);

  @BeforeEach
  void setUp() {
    playerRepository.clear();
    playerRepository.save(faker);
  }

  ChangePlayerMainRoleUseCase createUseCase() {
    return new ChangePlayerMainRoleUseCase(playerRepository);
  }

  @Nested
  class Scenario_HappyPath {
    ChangePlayerMainRoleInput input = new ChangePlayerMainRoleInput(faker.getId(), Role.TOP);

    @Test
    void shouldChangePlayerRole() {
      var useCase = createUseCase();
      useCase.execute(input);

      var player = playerRepository.findById(faker.getId()).get();
      assertEquals(Role.TOP, player.getMainRole());
    }
  }

  @Nested
  class Scenario_TheUserIsNotFound {
    ChangePlayerMainRoleInput input = new ChangePlayerMainRoleInput(
        UUID.fromString("00000000-0000-0000-0000-000000000000"),
        Role.TOP
    );

    @Test
    void shouldThrow() {
      var useCase = createUseCase();
      var exception = assertThrows(NotFoundException.class, () -> useCase.execute(input));
      assertEquals("Player not found", exception.getMessage());
    }
  }

}
