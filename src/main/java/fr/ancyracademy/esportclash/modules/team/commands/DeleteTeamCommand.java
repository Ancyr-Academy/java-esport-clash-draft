package fr.ancyracademy.esportclash.modules.team.commands;

import an.awesome.pipelinr.Command;

public class DeleteTeamCommand implements Command<Void> {
  private final String id;

  public DeleteTeamCommand(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }
}
