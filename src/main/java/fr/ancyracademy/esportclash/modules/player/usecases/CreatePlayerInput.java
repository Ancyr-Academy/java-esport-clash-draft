package fr.ancyracademy.esportclash.modules.player.usecases;

import fr.ancyracademy.esportclash.modules.player.model.Role;

public class CreatePlayerInput {
  private final String name;
  private final Role mainRole;

  public CreatePlayerInput(String name, Role role) {
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