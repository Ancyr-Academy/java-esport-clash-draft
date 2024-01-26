package fr.ancyracademy.javaesportclash.modules.player.spring;


import fr.ancyracademy.javaesportclash.modules.player.adapters.mongo.MongoPlayerDataAccessor;
import fr.ancyracademy.javaesportclash.modules.player.adapters.mongo.MongoPlayerRepository;
import fr.ancyracademy.javaesportclash.modules.player.ports.PlayerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdapterConfiguration {
  @Bean
  public PlayerRepository playerRepository(MongoPlayerDataAccessor dataAccessor) {
    return new MongoPlayerRepository(dataAccessor);
  }
}
