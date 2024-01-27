package fr.ancyracademy.esportclash.modules.player.e2e;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.ancyracademy.esportclash.PostgreSQLDbConfiguration;
import fr.ancyracademy.esportclash.modules.player.spring.dto.CreatePlayerDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@Import(PostgreSQLDbConfiguration.class)
public class CreatePlayerE2ETests {
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void shouldCreatePlayer() throws Exception {
    var player = new CreatePlayerDTO();
    player.setName("Faker");
    player.setMainRole("MID");

    mockMvc
        .perform(MockMvcRequestBuilders.post("/players")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(player)))
        .andExpect(MockMvcResultMatchers.status().isCreated());
  }
}
