package fr.ancyracademy.esportclash.modules.team.queries;

import an.awesome.pipelinr.Command;
import fr.ancyracademy.esportclash.modules.player.model.Player;
import fr.ancyracademy.esportclash.modules.player.ports.PlayerRepository;
import fr.ancyracademy.esportclash.modules.team.model.Team;
import fr.ancyracademy.esportclash.modules.team.ports.TeamRepository;
import fr.ancyracademy.esportclash.modules.team.viewmodel.TeamViewModel;

import java.util.List;

public class GetTeamByIdQueryHandler implements Command.Handler<GetTeamByIdQuery, TeamViewModel> {
  private final TeamRepository teamRepository;

  private final PlayerRepository playerRepository;

  public GetTeamByIdQueryHandler(TeamRepository teamRepository, PlayerRepository playerRepository) {
    this.teamRepository = teamRepository;
    this.playerRepository = playerRepository;
  }

  @Override
  public TeamViewModel handle(GetTeamByIdQuery getTeamByIdQuery) throws RuntimeException {
    var team = teamRepository.findById(getTeamByIdQuery.getId()).orElseThrow();
    var players = getPlayers(team);
    var members = toTeamMembers(team, players);

    return new TeamViewModel(team.getId(), team.getName(), members);
  }

  private List<TeamViewModel.TeamMember> toTeamMembers(Team team, List<Player> players) {
    return players.stream().map(
        player -> {
          var member = team.getMembers().stream().filter(m -> m.getPlayerId().equals(player.getId())).findFirst().orElseThrow();
          var role = member.getRole();
          return new TeamViewModel.TeamMember(player.getId(), player.getName(), role.toString());
        }
    ).toList();
  }

  private List<Player> getPlayers(Team team) {
    return team.getMembers().stream().map(
        member -> playerRepository.findById(member.getPlayerId()).orElseThrow()
    ).toList();
  }
}
