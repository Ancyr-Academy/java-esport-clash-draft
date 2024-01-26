package fr.ancyracademy.javaesportclash.modules.player.usecases;

import fr.ancyracademy.javaesportclash.modules.player.adapters.ram.InMemoryPlayerRepository;
import fr.ancyracademy.javaesportclash.modules.player.model.Player;
import fr.ancyracademy.javaesportclash.modules.player.model.Role;
import fr.ancyracademy.javaesportclash.shared.IdResponse;
import fr.ancyracademy.javaesportclash.shared.id.FixedIdProvider;
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

  CreatePlayerUseCase createUseCase() {
    return new CreatePlayerUseCase(idProvider, playerRepository);
  }

  @Nested
  class HappyPath {
    CreatePlayerInput input = new CreatePlayerInput("Faker", "MID");

    @Test
    void shouldReturnId() {
      CreatePlayerUseCase createPlayer = createUseCase();
      IdResponse response = createPlayer.execute(input);

      assertEquals(idProvider.generate(), response.getId());
    }

    @Test
    void shouldSavePlayer() {
      CreatePlayerUseCase createPlayer = createUseCase();
      createPlayer.execute(input);

      Optional<Player> player = playerRepository.findById(idProvider.generate());
      assertTrue(player.isPresent());

      assertEquals("Faker", player.get().getName());
      assertEquals(Role.MID, player.get().getMainRole());
    }
  }

}
