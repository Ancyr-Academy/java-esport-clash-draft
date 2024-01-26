package fr.ancyracademy.javaesportclash.modules.player.usecases;

import fr.ancyracademy.javaesportclash.modules.player.model.Role;

import java.util.UUID;

public class ChangePlayerMainRoleInput {

  private final UUID id;

  private final Role mainRole;

  public ChangePlayerMainRoleInput(UUID id, Role mainRole) {
    this.id = id;
    this.mainRole = mainRole;
  }

  public UUID getId() {
    return id;
  }

  public Role getMainRole() {
    return mainRole;
  }
}
