package fr.ancyracademy.javaesportclash.modules.team.usescases;

public class CreateTeamInput {
  private final String name;

  CreateTeamInput(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
