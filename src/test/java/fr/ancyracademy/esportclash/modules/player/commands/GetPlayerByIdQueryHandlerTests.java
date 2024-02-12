package fr.ancyracademy.esportclash.modules.player.commands;

import fr.ancyracademy.esportclash.modules.player.adapters.ram.InMemoryPlayerRepository;
import fr.ancyracademy.esportclash.modules.player.model.Player;
import fr.ancyracademy.esportclash.modules.player.model.Role;
import fr.ancyracademy.esportclash.modules.player.queries.GetPlayerByIdQuery;
import fr.ancyracademy.esportclash.modules.player.queries.GetPlayerByIdQueryHandler;
import fr.ancyracademy.esportclash.shared.exceptions.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class GetPlayerByIdQueryHandlerTests {
  InMemoryPlayerRepository playerRepository = new InMemoryPlayerRepository();

  Player faker = new Player("faker", "Faker", Role.MID);

  @BeforeEach
  void setUp() {
    playerRepository.clear();
    playerRepository.save(faker);
  }

  GetPlayerByIdQueryHandler createUseCase() {
    return new GetPlayerByIdQueryHandler(playerRepository);
  }

  @Nested
  class Scenario_HappyPath {
    GetPlayerByIdQuery input = new GetPlayerByIdQuery(faker.getId());

    @Test
    void shouldReturnThePlayers() {
      var useCase = createUseCase();
      var result = useCase.handle(input);

      assertEquals(faker.getId(), result.getId());
      assertEquals(faker.getName(), result.getName());
      assertEquals(faker.getMainRole().toString(), result.getMainRole());
    }
  }

  @Nested
  class Scenario_TheUserIsNotFound {
    GetPlayerByIdQuery input = new GetPlayerByIdQuery("random-id");

    @Test
    void shouldThrow() {
      var useCase = createUseCase();
      var exception = assertThrows(NotFoundException.class, () -> useCase.handle(input));
      assertEquals("Player not found", exception.getMessage());
    }
  }

}
