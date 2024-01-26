package fr.ancyracademy.javaesportclash.modules.player.spring.configuration;

import fr.ancyracademy.javaesportclash.modules.player.ports.PlayerRepository;
import fr.ancyracademy.javaesportclash.modules.player.usecases.CreatePlayerUseCase;
import fr.ancyracademy.javaesportclash.shared.id.IdProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCasesConfiguration {

  @Bean
  public CreatePlayerUseCase createPlayerUseCase(
      IdProvider idProvider,
      PlayerRepository playerRepository
  ) {
    return new CreatePlayerUseCase(
        idProvider,
        playerRepository
    );
  }
}
