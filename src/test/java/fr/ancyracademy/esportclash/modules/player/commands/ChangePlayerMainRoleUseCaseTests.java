package fr.ancyracademy.esportclash.modules.player.commands;

import fr.ancyracademy.esportclash.modules.player.adapters.ram.InMemoryPlayerRepository;
import fr.ancyracademy.esportclash.modules.player.model.Player;
import fr.ancyracademy.esportclash.modules.player.model.Role;
import fr.ancyracademy.esportclash.shared.exceptions.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ChangePlayerMainRoleUseCaseTests {
  InMemoryPlayerRepository playerRepository = new InMemoryPlayerRepository();

  Player faker = new Player("faker", "Faker", Role.MID);

  @BeforeEach
  void setUp() {
    playerRepository.clear();
    playerRepository.save(faker);
  }

  ChangePlayerMainRoleCommandHandler createUseCase() {
    return new ChangePlayerMainRoleCommandHandler(playerRepository);
  }

  @Nested
  class Scenario_HappyPath {
    ChangePlayerMainRoleCommand input = new ChangePlayerMainRoleCommand(faker.getId(), Role.TOP);

    @Test
    void shouldChangePlayerRole() {
      var useCase = createUseCase();
      useCase.handle(input);

      var player = playerRepository.findById(faker.getId()).get();
      assertEquals(Role.TOP, player.getMainRole());
    }
  }

  @Nested
  class Scenario_TheUserIsNotFound {
    ChangePlayerMainRoleCommand input = new ChangePlayerMainRoleCommand(
        "top",
        Role.TOP
    );

    @Test
    void shouldThrow() {
      var useCase = createUseCase();
      var exception = assertThrows(NotFoundException.class, () -> useCase.handle(input));
      assertEquals("Player not found", exception.getMessage());
    }
  }

}
