package fr.ancyracademy.esportclash.modules.player.commands;

import fr.ancyracademy.esportclash.modules.player.adapters.ram.InMemoryPlayerRepository;
import fr.ancyracademy.esportclash.modules.player.model.Player;
import fr.ancyracademy.esportclash.modules.player.model.Role;
import fr.ancyracademy.esportclash.shared.IdResponse;
import fr.ancyracademy.esportclash.shared.id.FixedIdProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class CreatePlayerUseCaseTests {
  FixedIdProvider idProvider = new FixedIdProvider();
  InMemoryPlayerRepository playerRepository = new InMemoryPlayerRepository();


  @BeforeEach
  void setUp() {
    playerRepository.clear();
  }

  CreatePlayerCommandHandler createUseCase() {
    return new CreatePlayerCommandHandler(idProvider, playerRepository);
  }

  @Nested
  class HappyPath {
    CreatePlayerCommand input = new CreatePlayerCommand("Faker", Role.MID);

    @Test
    void shouldReturnId() {
      var useCase = createUseCase();
      IdResponse response = useCase.handle(input);

      assertEquals(idProvider.generate(), response.getId());
    }

    @Test
    void shouldSavePlayer() {
      var useCase = createUseCase();
      useCase.handle(input);

      Optional<Player> player = playerRepository.findById(idProvider.generate());
      assertTrue(player.isPresent());

      assertEquals("Faker", player.get().getName());
      assertEquals(Role.MID, player.get().getMainRole());
    }
  }

}
