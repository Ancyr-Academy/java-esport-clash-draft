package fr.ancyracademy.esportclash.modules.team.spring.dto;

import jakarta.validation.constraints.NotNull;

public class AddPlayerToTeamDTO {

  @NotNull
  private String playerId;

  @NotNull
  private String role;

  public String getPlayerId() {
    return playerId;
  }

  public void setPlayerId(String playerId) {
    this.playerId = playerId;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }
}
