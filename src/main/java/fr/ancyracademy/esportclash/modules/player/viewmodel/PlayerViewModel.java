package fr.ancyracademy.esportclash.modules.player.viewmodel;

import fr.ancyracademy.esportclash.modules.player.model.Player;

public class PlayerViewModel {
  private final String id;
  private final String name;
  private final String mainRole;
  
  public PlayerViewModel(Player player) {
    this.id = player.getId().toString();
    this.name = player.getName();
    this.mainRole = player.getMainRole().toString();
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getMainRole() {
    return mainRole;
  }
}
