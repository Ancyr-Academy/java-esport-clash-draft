package fr.ancyracademy.esportclash.modules.player.adapters.sql;

import fr.ancyracademy.esportclash.PostgreSQLDbConfiguration;
import fr.ancyracademy.esportclash.modules.player.model.Player;
import fr.ancyracademy.esportclash.modules.player.model.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@Import(PostgreSQLDbConfiguration.class)
public class SQLPlayerRepositoryTests {
  @Autowired
  private SQLPlayerRepository repository;

  @BeforeEach
  void setUp() {
    repository.clear();
  }

  @Test
  void shouldSavePlayer() {
    var seed = new Player("faker", "Faker", Role.MID);
    repository.save(seed);

    var player = repository.findById(seed.getId());
    assertTrue(player.isPresent());
    assertEquals(seed.getId(), player.get().getId());
    assertEquals(seed.getName(), player.get().getName());
    assertEquals(seed.getMainRole(), player.get().getMainRole());
  }

  @Test
  void shouldDeletePlayer() {
    var seed = new Player("faker", "Faker", Role.MID);
    repository.save(seed);
    repository.delete(seed);

    var player = repository.findById(seed.getId());
    assertTrue(player.isEmpty());
  }
}
