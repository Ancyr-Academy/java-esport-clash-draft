package fr.ancyracademy.esportclash.modules.team.commands;

import an.awesome.pipelinr.Command;

public class RemovePlayerFromTeamCommand implements Command<Void> {
  private final String playerId;

  public RemovePlayerFromTeamCommand(String playerId) {
    this.playerId = playerId;
  }


  public String getPlayerId() {
    return playerId;
  }
}
