package fr.ancyracademy.javaesportclash.modules.player.usecases;

public class CreatePlayerInput {
  private final String name;
  private final String mainRole;

  public CreatePlayerInput(String name, String role) {
    this.name = name;
    this.mainRole = role;
  }

  public String getName() {
    return name;
  }

  public String getMainRole() {
    return mainRole;
  }
}
