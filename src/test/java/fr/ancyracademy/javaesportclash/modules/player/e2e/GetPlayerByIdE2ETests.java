package fr.ancyracademy.javaesportclash.modules.player.e2e;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.ancyracademy.javaesportclash.PostgreSQLDbConfiguration;
import fr.ancyracademy.javaesportclash.modules.player.model.Player;
import fr.ancyracademy.javaesportclash.modules.player.model.Role;
import fr.ancyracademy.javaesportclash.modules.player.ports.PlayerRepository;
import fr.ancyracademy.javaesportclash.modules.player.viewmodel.PlayerViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
@Import(PostgreSQLDbConfiguration.class)
public class GetPlayerByIdE2ETests {
  Player faker = new Player(UUID.randomUUID(), "Faker", Role.MID);

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper objectMapper;
  @Autowired
  private PlayerRepository playerRepository;

  @BeforeEach
  void setUp() {
    playerRepository.clear();
    playerRepository.save(faker);
  }

  @Test
  public void whenThePlayerExists_shouldGetPlayer() throws Exception {
    var result = mockMvc
        .perform(MockMvcRequestBuilders.get("/players/{id}", faker.getId()))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn();

    var player = objectMapper.readValue(result.getResponse().getContentAsString(), PlayerViewModel.class);

    assertEquals(faker.getId().toString(), player.getId());
    assertEquals(faker.getName(), player.getName());
    assertEquals(faker.getMainRole().toString(), player.getMainRole());
  }

  @Test
  public void whenThePlayerDoesNotExists_shouldThrow404() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.get("/players/{id}", UUID.fromString("00000000-0000-0000-0000-000000000000")))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
  }
}
