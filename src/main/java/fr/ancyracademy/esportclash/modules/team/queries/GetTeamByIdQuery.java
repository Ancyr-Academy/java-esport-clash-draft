package fr.ancyracademy.esportclash.modules.team.queries;

import an.awesome.pipelinr.Command;
import fr.ancyracademy.esportclash.modules.team.viewmodel.TeamViewModel;

public class GetTeamByIdQuery implements Command<TeamViewModel> {
  private final String id;

  public GetTeamByIdQuery(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }
}