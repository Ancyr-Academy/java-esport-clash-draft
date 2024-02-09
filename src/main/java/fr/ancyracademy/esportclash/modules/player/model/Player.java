package fr.ancyracademy.esportclash.modules.player.model;

import jakarta.persistence.*;

@Entity
@Table(name = "players")
public class Player {
  @Id
  private String id;

  @Column
  private String name;

  @Enumerated(EnumType.STRING)
  @Column
  private Role mainRole;

  public Player() {

  }

  public Player(String id, String name, Role mainRole) {
    this.id = id;
    this.name = name;
    this.mainRole = mainRole;
  }

  public Player(Player other) {
    this.id = other.id;
    this.name = other.name;
    this.mainRole = other.mainRole;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
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
