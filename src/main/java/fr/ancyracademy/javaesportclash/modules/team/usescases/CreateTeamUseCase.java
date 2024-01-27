package fr.ancyracademy.javaesportclash.modules.team.usescases;

import fr.ancyracademy.javaesportclash.modules.team.model.Team;
import fr.ancyracademy.javaesportclash.modules.team.ports.TeamRepository;
import fr.ancyracademy.javaesportclash.shared.IdResponse;
import fr.ancyracademy.javaesportclash.shared.UseCase;
import fr.ancyracademy.javaesportclash.shared.id.IdProvider;

import java.util.ArrayList;

public class CreateTeamUseCase implements UseCase<CreateTeamInput, IdResponse> {
  private final TeamRepository teamRepository;

  private final IdProvider idProvider;

  public CreateTeamUseCase(TeamRepository teamRepository, IdProvider idProvider) {
    this.teamRepository = teamRepository;
    this.idProvider = idProvider;
  }

  @Override
  public IdResponse execute(CreateTeamInput input) {
    Team team = new Team(
        this.idProvider.generate(),
        input.getName(),
        new ArrayList<>()
    );

    this.teamRepository.save(team);

    return new IdResponse(team.getId());
  }
}
