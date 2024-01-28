package fr.ancyracademy.esportclash.modules.player.spring.configuration;


import fr.ancyracademy.esportclash.modules.player.adapters.sql.SQLPlayerDataAccessor;
import fr.ancyracademy.esportclash.modules.player.adapters.sql.SQLPlayerRepository;
import fr.ancyracademy.esportclash.modules.player.ports.PlayerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PlayerAdapterConfiguration {
  @Bean
  public PlayerRepository playerRepository(SQLPlayerDataAccessor dataAccessor) {
    return new SQLPlayerRepository(dataAccessor);
  }
}
