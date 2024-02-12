package fr.ancyracademy.esportclash.modules.player.commands;

import an.awesome.pipelinr.Command;
import fr.ancyracademy.esportclash.modules.player.model.Role;

public class ChangePlayerMainRoleCommand implements Command<Void> {

  private final String id;

  private final Role mainRole;

  public ChangePlayerMainRoleCommand(String id, Role mainRole) {
    this.id = id;
    this.mainRole = mainRole;
  }

  public String getId() {
    return id;
  }

  public Role getMainRole() {
    return mainRole;
  }
}
