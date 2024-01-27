package fr.ancyracademy.esportclash.modules.player.usecases;

import java.util.UUID;

public class GetPlayerByIdInput {
  private final UUID id;


  public GetPlayerByIdInput(UUID id) {
    this.id = id;
  }

  public UUID getId() {
    return id;
  }
}
