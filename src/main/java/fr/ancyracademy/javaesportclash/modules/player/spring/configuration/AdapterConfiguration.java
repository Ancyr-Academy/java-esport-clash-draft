package fr.ancyracademy.javaesportclash.modules.player.spring.configuration;


import fr.ancyracademy.javaesportclash.modules.player.adapters.sql.SQLPlayerDataAccessor;
import fr.ancyracademy.javaesportclash.modules.player.adapters.sql.SQLPlayerRepository;
import fr.ancyracademy.javaesportclash.modules.player.ports.PlayerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdapterConfiguration {
  @Bean
  public PlayerRepository playerRepository(SQLPlayerDataAccessor dataAccessor) {
    return new SQLPlayerRepository(dataAccessor);
  }
}
