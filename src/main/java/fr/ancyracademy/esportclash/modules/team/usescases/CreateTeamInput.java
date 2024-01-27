package fr.ancyracademy.esportclash.modules.team.usescases;

public class CreateTeamInput {
  private final String name;

  public CreateTeamInput(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
