package fr.ancyracademy.esportclash.modules.player.viewmodel;

import fr.ancyracademy.esportclash.modules.player.model.Player;

public class PlayerViewModel {
  private String id;
  private String name;
  private String mainRole;

  public PlayerViewModel() {

  }

  public PlayerViewModel(Player player) {
    this.id = player.getId();
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
