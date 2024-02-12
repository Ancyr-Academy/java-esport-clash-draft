package fr.ancyracademy.esportclash.modules.team.commands;

import an.awesome.pipelinr.Command;
import fr.ancyracademy.esportclash.modules.player.model.Role;

public class AddPlayerToTeamCommand implements Command<Void> {
  private final String teamId;

  private final String playerId;

  private final Role role;

  public AddPlayerToTeamCommand(String teamId, String playerId, Role role) {
    this.teamId = teamId;
    this.playerId = playerId;
    this.role = role;
  }

  public String getTeamId() {
    return teamId;
  }

  public String getPlayerId() {
    return playerId;
  }

  public Role getRole() {
    return role;
  }
}
