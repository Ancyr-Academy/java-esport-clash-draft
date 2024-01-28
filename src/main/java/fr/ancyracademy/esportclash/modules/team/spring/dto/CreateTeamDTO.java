package fr.ancyracademy.esportclash.modules.team.spring.dto;

import jakarta.validation.constraints.NotNull;

public class CreateTeamDTO {
  @NotNull
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
