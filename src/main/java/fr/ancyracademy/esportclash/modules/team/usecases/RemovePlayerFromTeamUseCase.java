package fr.ancyracademy.esportclash.modules.team.usecases;

import fr.ancyracademy.esportclash.modules.player.ports.PlayerRepository;
import fr.ancyracademy.esportclash.modules.team.ports.TeamRepository;
import fr.ancyracademy.esportclash.shared.UseCase;
import fr.ancyracademy.esportclash.shared.exceptions.NotFoundException;

public class RemovePlayerFromTeamUseCase implements UseCase<RemovePlayerFromTeamInput, Void> {
  private final PlayerRepository playerRepository;
  private final TeamRepository teamRepository;

  public RemovePlayerFromTeamUseCase(PlayerRepository playerRepository, TeamRepository teamRepository) {
    this.playerRepository = playerRepository;
    this.teamRepository = teamRepository;
  }

  @Override
  public Void execute(RemovePlayerFromTeamInput input) {
    var player = this.playerRepository.findById(input.playerId()).orElseThrow(
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
