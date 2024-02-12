package fr.ancyracademy.esportclash.modules.team.commands;

import an.awesome.pipelinr.Command;
import fr.ancyracademy.esportclash.modules.team.model.Team;
import fr.ancyracademy.esportclash.modules.team.ports.TeamRepository;

public class DeleteTeamCommandHandler implements Command.Handler<DeleteTeamCommand, Void> {
  private final TeamRepository teamRepository;

  public DeleteTeamCommandHandler(TeamRepository teamRepository) {
    this.teamRepository = teamRepository;
  }

  @Override
  public Void handle(DeleteTeamCommand input) {
    Team team = this.teamRepository.findById(input.getId()).orElseThrow();
    this.teamRepository.delete(team);

    return null;
  }
}
