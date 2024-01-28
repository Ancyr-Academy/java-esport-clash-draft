package fr.ancyracademy.esportclash.modules.team.spring.configuration;

import fr.ancyracademy.esportclash.modules.player.ports.PlayerRepository;
import fr.ancyracademy.esportclash.modules.team.ports.TeamRepository;
import fr.ancyracademy.esportclash.modules.team.usecases.AddPlayerToTeamUseCase;
import fr.ancyracademy.esportclash.modules.team.usecases.CreateTeamUseCase;
import fr.ancyracademy.esportclash.modules.team.usecases.DeleteTeamUseCase;
import fr.ancyracademy.esportclash.modules.team.usecases.RemovePlayerFromTeamUseCase;
import fr.ancyracademy.esportclash.shared.id.IdProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TeamUseCasesConfiguration {
  @Bean
  public CreateTeamUseCase createTeamUseCase(
      TeamRepository teamRepository,
      IdProvider idProvider
  ) {
    return new CreateTeamUseCase(
        teamRepository,
        idProvider
    );
  }

  @Bean
  public DeleteTeamUseCase deleteTeamUseCase(
      TeamRepository teamRepository
  ) {
    return new DeleteTeamUseCase(
        teamRepository
    );
  }

  @Bean
  public AddPlayerToTeamUseCase addPlayerToTeamUseCase(
      PlayerRepository playerRepository,
      TeamRepository teamRepository
  ) {
    return new AddPlayerToTeamUseCase(
        playerRepository,
        teamRepository
    );
  }

  @Bean
  public RemovePlayerFromTeamUseCase removePlayerFromTeamUseCase(
      PlayerRepository playerRepository,
      TeamRepository teamRepository
  ) {
    return new RemovePlayerFromTeamUseCase(
        playerRepository,
        teamRepository
    );
  }
}
