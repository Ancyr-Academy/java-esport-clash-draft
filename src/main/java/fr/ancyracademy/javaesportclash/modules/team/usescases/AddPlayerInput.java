package fr.ancyracademy.javaesportclash.modules.team.usescases;

import fr.ancyracademy.javaesportclash.modules.player.model.Role;

import java.util.UUID;

public class AddPlayerInput {
  private final UUID teamId;

  private final UUID playerId;

  private final Role role;

  AddPlayerInput(UUID teamId, UUID playerId, Role role) {
    this.teamId = teamId;
    this.playerId = playerId;
    this.role = role;
  }

  public UUID getTeamId() {
    return teamId;
  }

  public UUID getPlayerId() {
    return playerId;
  }

  public Role getRole() {
    return role;
  }
}
