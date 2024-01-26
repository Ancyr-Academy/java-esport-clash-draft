package fr.ancyracademy.javaesportclash.modules.player.spring.dto;

import jakarta.validation.constraints.NotNull;

public class ChangePlayerMainRoleDTO {
  @NotNull
  private String mainRole;

  public ChangePlayerMainRoleDTO() {
  }

  public String getMainRole() {
    return mainRole;
  }

  public void setMainRole(String mainRole) {
    this.mainRole = mainRole;
  }

}
