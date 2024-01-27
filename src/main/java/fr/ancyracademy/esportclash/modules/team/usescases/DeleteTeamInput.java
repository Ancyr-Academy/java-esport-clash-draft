package fr.ancyracademy.esportclash.modules.team.usescases;

import java.util.UUID;

public class DeleteTeamInput {
  private final UUID id;

  public DeleteTeamInput(UUID id) {
    this.id = id;
  }

  public UUID getId() {
    return id;
  }
}