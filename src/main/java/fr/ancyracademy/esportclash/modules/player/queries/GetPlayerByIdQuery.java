package fr.ancyracademy.esportclash.modules.player.queries;

import an.awesome.pipelinr.Command;
import fr.ancyracademy.esportclash.modules.player.viewmodel.PlayerViewModel;

public class GetPlayerByIdQuery implements Command<PlayerViewModel> {
  private final String id;

  public GetPlayerByIdQuery(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }
}
