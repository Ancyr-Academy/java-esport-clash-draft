package fr.ancyracademy.esportclash.modules.team.commands;

import an.awesome.pipelinr.Command;
import fr.ancyracademy.esportclash.modules.team.model.Team;
import fr.ancyracademy.esportclash.modules.team.ports.TeamRepository;
import fr.ancyracademy.esportclash.shared.IdResponse;
import fr.ancyracademy.esportclash.shared.id.IdProvider;

public class CreateTeamCommandHandler implements Command.Handler<CreateTeamCommand, IdResponse> {
  private final TeamRepository teamRepository;

  private final IdProvider idProvider;

  public CreateTeamCommandHandler(TeamRepository teamRepository, IdProvider idProvider) {
    this.teamRepository = teamRepository;
    this.idProvider = idProvider;
  }

  @Override
  public IdResponse handle(CreateTeamCommand input) {
    Team team = new Team(
        this.idProvider.generate(),
        input.getName()
    );

    this.teamRepository.save(team);

    return new IdResponse(team.getId());
  }
}
