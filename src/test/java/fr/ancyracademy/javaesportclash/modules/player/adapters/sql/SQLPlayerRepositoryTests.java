package fr.ancyracademy.javaesportclash.modules.player.adapters.sql;

import fr.ancyracademy.javaesportclash.PostgreSQLDbConfiguration;
import fr.ancyracademy.javaesportclash.modules.player.model.Player;
import fr.ancyracademy.javaesportclash.modules.player.model.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.UUID;

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
    var seed = new Player(UUID.randomUUID(), "Faker", Role.MID);
    repository.save(seed);

    var player = repository.findById(seed.getId());
    assertTrue(player.isPresent());
    assertEquals(seed.getId(), player.get().getId());
    assertEquals(seed.getName(), player.get().getName());
    assertEquals(seed.getMainRole(), player.get().getMainRole());
  }

  @Test
  void shouldDeletePlayer() {
    var seed = new Player(UUID.randomUUID(), "Faker", Role.MID);
    repository.save(seed);
    repository.delete(seed);

    var player = repository.findById(seed.getId());
    assertTrue(player.isEmpty());
  }
}
