package fr.ancyracademy.esportclash.modules.team.usescases;

import fr.ancyracademy.esportclash.modules.player.model.Player;
import fr.ancyracademy.esportclash.modules.player.ports.PlayerRepository;
import fr.ancyracademy.esportclash.modules.team.model.Team;
import fr.ancyracademy.esportclash.modules.team.ports.TeamRepository;
import fr.ancyracademy.esportclash.shared.UseCase;

import java.util.Optional;

public class AddPlayerUseCase implements UseCase<AddPlayerInput, Void> {
  private final PlayerRepository playerRepository;
  private final TeamRepository teamRepository;

  public AddPlayerUseCase(PlayerRepository playerRepository, TeamRepository teamRepository) {
    this.playerRepository = playerRepository;
    this.teamRepository = teamRepository;
  }

  @Override
  public Void execute(AddPlayerInput input) {
    Player player = this.playerRepository.findById(input.getPlayerId()).orElseThrow();
    Optional<Team> playerCurrentTeam = this.teamRepository.findByPlayerId(player.getId());

    if (playerCurrentTeam.isPresent()) {
      if (playerCurrentTeam.get().getId().equals(input.getTeamId())) {
        // The action is already done
        return null;
      }

      throw new RuntimeException("Player already in another team");
    }

    Team team = this.teamRepository.findById(input.getTeamId()).orElseThrow();
    team.join(player.getId(), input.getRole());

    return null;
  }
}
