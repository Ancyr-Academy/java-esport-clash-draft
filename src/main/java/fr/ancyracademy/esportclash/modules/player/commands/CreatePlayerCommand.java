package fr.ancyracademy.esportclash.modules.player.commands;

import an.awesome.pipelinr.Command;
import fr.ancyracademy.esportclash.modules.player.model.Role;
import fr.ancyracademy.esportclash.shared.IdResponse;

public class CreatePlayerCommand implements Command<IdResponse> {
  private final String name;
  private final Role mainRole;

  public CreatePlayerCommand(String name, Role role) {
    this.name = name;
    this.mainRole = role;
  }

  public String getName() {
    return name;
  }

  public Role getMainRole() {
    return mainRole;
  }
}
