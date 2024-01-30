package fr.ancyracademy.esportclash.modules.team.usecases;

import fr.ancyracademy.esportclash.modules.player.model.Player;
import fr.ancyracademy.esportclash.modules.player.ports.PlayerRepository;
import fr.ancyracademy.esportclash.modules.team.model.Team;
import fr.ancyracademy.esportclash.modules.team.ports.TeamRepository;
import fr.ancyracademy.esportclash.modules.team.viewmodel.TeamViewModel;
import fr.ancyracademy.esportclash.shared.UseCase;

import java.util.List;

public class GetTeamByIdUseCase implements UseCase<GetTeamByIdInput, TeamViewModel> {
  private final TeamRepository teamRepository;

  private final PlayerRepository playerRepository;

  public GetTeamByIdUseCase(TeamRepository teamRepository, PlayerRepository playerRepository) {
    this.teamRepository = teamRepository;
    this.playerRepository = playerRepository;
  }

  @Override
  public TeamViewModel execute(GetTeamByIdInput getTeamByIdInput) throws RuntimeException {
    var team = teamRepository.findById(getTeamByIdInput.id()).orElseThrow();
    var players = getPlayers(team);
    var members = toTeamMembers(team, players);

    return new TeamViewModel(team.getId(), team.getName(), members);
  }

  private List<TeamViewModel.TeamMember> toTeamMembers(Team team, List<Player> players) {
    return players.stream().map(
        player -> {
          var member = team.getMembers().stream().filter(m -> m.getId().equals(player.getId())).findFirst().orElseThrow();
          var role = member.getRole();
          return new TeamViewModel.TeamMember(player.getId(), player.getName(), role.toString());
        }
    ).toList();
  }

  private List<Player> getPlayers(Team team) {
    return team.getMembers().stream().map(
        member -> playerRepository.findById(member.getId()).orElseThrow()
    ).toList();
  }
}
