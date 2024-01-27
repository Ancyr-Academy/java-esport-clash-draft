package fr.ancyracademy.javaesportclash.modules.team.usescases;

import java.util.UUID;

public class DeleteTeamInput {
  private final UUID id;

  DeleteTeamInput(UUID id) {
    this.id = id;
  }

  public UUID getId() {
    return id;
  }
}
