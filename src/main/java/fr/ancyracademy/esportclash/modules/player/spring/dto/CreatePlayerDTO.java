package fr.ancyracademy.esportclash.modules.player.spring.dto;

import jakarta.validation.constraints.NotNull;

public class CreatePlayerDTO {
  @NotNull
  private String name;

  @NotNull
  private String mainRole;

  public CreatePlayerDTO() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getMainRole() {
    return mainRole;
  }

  public void setMainRole(String mainRole) {
    this.mainRole = mainRole;
  }
}
