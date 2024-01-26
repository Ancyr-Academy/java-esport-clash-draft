package fr.ancyracademy.javaesportclash.modules.player.spring.configuration;

import fr.ancyracademy.javaesportclash.modules.player.ports.PlayerRepository;
import fr.ancyracademy.javaesportclash.modules.player.usecases.ChangePlayerMainRoleUseCase;
import fr.ancyracademy.javaesportclash.modules.player.usecases.CreatePlayerUseCase;
import fr.ancyracademy.javaesportclash.modules.player.usecases.GetPlayerByIdUseCase;
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

  @Bean
  public ChangePlayerMainRoleUseCase changePlayerMainRoleUseCase(
      PlayerRepository playerRepository
  ) {
    return new ChangePlayerMainRoleUseCase(
        playerRepository
    );
  }

  @Bean
  public GetPlayerByIdUseCase getPlayerByIdUseCase(
      PlayerRepository playerRepository
  ) {
    return new GetPlayerByIdUseCase(
        playerRepository
    );
  }
}
