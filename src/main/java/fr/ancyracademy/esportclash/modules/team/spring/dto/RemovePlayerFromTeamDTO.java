package fr.ancyracademy.esportclash.modules.team.spring.dto;

import jakarta.validation.constraints.NotNull;

public class RemovePlayerFromTeamDTO {
  @NotNull
  private String playerId;

  public String getPlayerId() {
    return playerId;
  }

  public void setPlayerId(String playerId) {
    this.playerId = playerId;
  }
}
