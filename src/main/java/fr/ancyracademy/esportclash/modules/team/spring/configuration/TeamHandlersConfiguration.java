package fr.ancyracademy.esportclash.modules.team.spring.configuration;

import fr.ancyracademy.esportclash.modules.player.ports.PlayerRepository;
import fr.ancyracademy.esportclash.modules.team.commands.AddPlayerToTeamCommandHandler;
import fr.ancyracademy.esportclash.modules.team.commands.CreateTeamCommandHandler;
import fr.ancyracademy.esportclash.modules.team.commands.DeleteTeamCommandHandler;
import fr.ancyracademy.esportclash.modules.team.commands.RemovePlayerFromTeamCommandHandler;
import fr.ancyracademy.esportclash.modules.team.ports.TeamRepository;
import fr.ancyracademy.esportclash.modules.team.queries.GetTeamByIdQueryHandler;
import fr.ancyracademy.esportclash.shared.id.IdProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TeamHandlersConfiguration {
  @Bean
  public CreateTeamCommandHandler createTeamUseCase(
      TeamRepository teamRepository,
      IdProvider idProvider
  ) {
    return new CreateTeamCommandHandler(
        teamRepository,
        idProvider
    );
  }

  @Bean
  public DeleteTeamCommandHandler deleteTeamUseCase(
      TeamRepository teamRepository
  ) {
    return new DeleteTeamCommandHandler(
        teamRepository
    );
  }

  @Bean
  public AddPlayerToTeamCommandHandler addPlayerToTeamUseCase(
      PlayerRepository playerRepository,
      TeamRepository teamRepository
  ) {
    return new AddPlayerToTeamCommandHandler(
        playerRepository,
        teamRepository
    );
  }

  @Bean
  public RemovePlayerFromTeamCommandHandler removePlayerFromTeamUseCase(
      PlayerRepository playerRepository,
      TeamRepository teamRepository
  ) {
    return new RemovePlayerFromTeamCommandHandler(
        playerRepository,
        teamRepository
    );
  }

  @Bean
  public GetTeamByIdQueryHandler getTeamByIdUseCase(
      TeamRepository teamRepository,
      PlayerRepository playerRepository
  ) {
    return new GetTeamByIdQueryHandler(
        teamRepository,
        playerRepository
    );
  }
}
