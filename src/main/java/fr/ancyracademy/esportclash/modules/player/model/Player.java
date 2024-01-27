package fr.ancyracademy.esportclash.modules.player.model;

import java.util.UUID;

public class Player {
  private UUID id;
  private String name;
  private Role mainRole;

  public Player(UUID id, String name, Role mainRole) {
    this.id = id;
    this.name = name;
    this.mainRole = mainRole;
  }

  public Player(Player other) {
    this.id = other.id;
    this.name = other.name;
    this.mainRole = other.mainRole;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Role getMainRole() {
    return mainRole;
  }

  public void setMainRole(Role mainRole) {
    this.mainRole = mainRole;
  }
}
