package fr.ancyracademy.esportclash.modules.player.usecases;

public class GetPlayerByIdInput {
  private final String id;


  public GetPlayerByIdInput(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }
}
