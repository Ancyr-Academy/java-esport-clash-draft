package fr.ancyracademy.esportclash.modules.player.usecases;

import fr.ancyracademy.esportclash.modules.player.model.Role;

public class ChangePlayerMainRoleInput {

  private final String id;

  private final Role mainRole;

  public ChangePlayerMainRoleInput(String id, Role mainRole) {
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
