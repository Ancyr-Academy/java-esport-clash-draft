package fr.ancyracademy.esportclash.modules.team.commands;

import an.awesome.pipelinr.Command;
import fr.ancyracademy.esportclash.shared.IdResponse;

public class CreateTeamCommand implements Command<IdResponse> {
  private final String name;

  public CreateTeamCommand(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
