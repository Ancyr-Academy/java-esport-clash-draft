package fr.ancyracademy.esportclash.modules.team.usescases;

import fr.ancyracademy.esportclash.modules.team.model.Team;
import fr.ancyracademy.esportclash.modules.team.ports.TeamRepository;
import fr.ancyracademy.esportclash.shared.UseCase;

public class DeleteTeamUseCase implements UseCase<DeleteTeamInput, Void> {
  private final TeamRepository teamRepository;

  public DeleteTeamUseCase(TeamRepository teamRepository) {
    this.teamRepository = teamRepository;
  }

  @Override
  public Void execute(DeleteTeamInput input) {
    Team team = this.teamRepository.findById(input.getId()).orElseThrow();
    this.teamRepository.delete(team);

    return null;
  }
}
