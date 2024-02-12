package fr.ancyracademy.esportclash.modules.team.commands;

import an.awesome.pipelinr.Command;
import fr.ancyracademy.esportclash.modules.player.ports.PlayerRepository;
import fr.ancyracademy.esportclash.modules.team.ports.TeamRepository;
import fr.ancyracademy.esportclash.shared.exceptions.NotFoundException;

public class RemovePlayerFromTeamCommandHandler implements Command.Handler<RemovePlayerFromTeamCommand, Void> {
  private final PlayerRepository playerRepository;
  private final TeamRepository teamRepository;

  public RemovePlayerFromTeamCommandHandler(PlayerRepository playerRepository, TeamRepository teamRepository) {
    this.playerRepository = playerRepository;
    this.teamRepository = teamRepository;
  }

  @Override
  public Void handle(RemovePlayerFromTeamCommand input) {
    var player = this.playerRepository.findById(input.getPlayerId()).orElseThrow(
        () -> new NotFoundException("Player not found")
    );

    var team = this.teamRepository.findByPlayerId(player.getId()).orElseThrow(
        () -> new NotFoundException("This player is not in a team")
    );

    team.leave(player.getId());
    this.teamRepository.save(team);

    return null;
  }
}
