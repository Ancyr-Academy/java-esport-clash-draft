package fr.ancyracademy.esportclash.modules.team.usecases;

import fr.ancyracademy.esportclash.modules.team.model.Team;
import fr.ancyracademy.esportclash.modules.team.ports.TeamRepository;
import fr.ancyracademy.esportclash.shared.IdResponse;
import fr.ancyracademy.esportclash.shared.UseCase;
import fr.ancyracademy.esportclash.shared.id.IdProvider;

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
        input.name(),
        new ArrayList<>()
    );

    this.teamRepository.save(team);

    return new IdResponse(team.getId());
  }
}
