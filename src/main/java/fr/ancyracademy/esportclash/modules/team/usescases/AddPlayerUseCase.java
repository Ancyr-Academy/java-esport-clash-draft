package fr.ancyracademy.esportclash.modules.team.usescases;

import fr.ancyracademy.esportclash.modules.player.model.Player;
import fr.ancyracademy.esportclash.modules.player.ports.PlayerRepository;
import fr.ancyracademy.esportclash.modules.team.model.Team;
import fr.ancyracademy.esportclash.modules.team.ports.TeamRepository;
import fr.ancyracademy.esportclash.shared.UseCase;
import fr.ancyracademy.esportclash.shared.exceptions.NotFoundException;

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
    Player player = this.playerRepository.findById(input.getPlayerId()).orElseThrow(
        () -> new NotFoundException("Player not found")
    );
    Optional<Team> playerCurrentTeam = this.teamRepository.findByPlayerId(player.getId());

    if (playerCurrentTeam.isPresent()) {
      var team = playerCurrentTeam.get();
      if (team.getId().equals(input.getTeamId())) {
        // The user is already in the team, we can return early
        return null;
      }

      throw new RuntimeException("Player already in another team");
    }

    Team team = this.teamRepository.findById(input.getTeamId()).orElseThrow(
        () -> new NotFoundException("Team not found")
    );

    team.join(player.getId(), input.getRole());
    this.teamRepository.save(team);

    return null;
  }
}
