package fr.ancyracademy.esportclash.modules.player.spring.configuration;

import fr.ancyracademy.esportclash.modules.player.commands.ChangePlayerMainRoleCommandHandler;
import fr.ancyracademy.esportclash.modules.player.commands.CreatePlayerCommandHandler;
import fr.ancyracademy.esportclash.modules.player.ports.PlayerRepository;
import fr.ancyracademy.esportclash.modules.player.queries.GetPlayerByIdQueryHandler;
import fr.ancyracademy.esportclash.shared.id.IdProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PlayerUseCasesConfiguration {

  @Bean
  public CreatePlayerCommandHandler createPlayerUseCase(
      IdProvider idProvider,
      PlayerRepository playerRepository
  ) {
    return new CreatePlayerCommandHandler(
        idProvider,
        playerRepository
    );
  }

  @Bean
  public ChangePlayerMainRoleCommandHandler changePlayerMainRoleUseCase(
      PlayerRepository playerRepository
  ) {
    return new ChangePlayerMainRoleCommandHandler(
        playerRepository
    );
  }

  @Bean
  public GetPlayerByIdQueryHandler getPlayerByIdUseCase(
      PlayerRepository playerRepository
  ) {
    return new GetPlayerByIdQueryHandler(
        playerRepository
    );
  }
}
