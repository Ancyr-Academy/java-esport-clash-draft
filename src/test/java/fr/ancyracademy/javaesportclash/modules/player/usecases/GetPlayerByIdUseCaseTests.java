package fr.ancyracademy.javaesportclash.modules.player.usecases;

import fr.ancyracademy.javaesportclash.modules.player.adapters.ram.InMemoryPlayerRepository;
import fr.ancyracademy.javaesportclash.modules.player.model.Player;
import fr.ancyracademy.javaesportclash.modules.player.model.Role;
import fr.ancyracademy.javaesportclash.shared.exceptions.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class GetPlayerByIdUseCaseTests {
  InMemoryPlayerRepository playerRepository = new InMemoryPlayerRepository();

  Player faker = new Player(UUID.randomUUID(), "Faker", Role.MID);

  @BeforeEach
  void setUp() {
    playerRepository.clear();
    playerRepository.save(faker);
  }

  GetPlayerByIdUseCase createUseCase() {
    return new GetPlayerByIdUseCase(playerRepository);
  }

  @Nested
  class Scenario_HappyPath {
    GetPlayerByIdInput input = new GetPlayerByIdInput(faker.getId());

    @Test
    void shouldReturnThePlayers() {
      var useCase = createUseCase();
      var result = useCase.execute(input);

      assertEquals(faker.getId().toString(), result.getId());
      assertEquals(faker.getName(), result.getName());
      assertEquals(faker.getMainRole().toString(), result.getMainRole());
    }
  }

  @Nested
  class Scenario_TheUserIsNotFound {
    GetPlayerByIdInput input = new GetPlayerByIdInput(
        UUID.fromString("00000000-0000-0000-0000-000000000000")
    );

    @Test
    void shouldThrow() {
      var useCase = createUseCase();
      var exception = assertThrows(NotFoundException.class, () -> useCase.execute(input));
      assertEquals("Player not found", exception.getMessage());
    }
  }

}
