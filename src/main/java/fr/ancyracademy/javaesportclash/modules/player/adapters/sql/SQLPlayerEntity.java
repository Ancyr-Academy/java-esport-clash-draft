package fr.ancyracademy.javaesportclash.modules.player.adapters.sql;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "players")
public class SQLPlayerEntity {
  @Id
  private UUID id;

  private String name;

  private String mainRole;

  public SQLPlayerEntity() {
  }

  public SQLPlayerEntity(UUID id, String name, String mainRole) {
    this.id = id;
    this.name = name;
    this.mainRole = mainRole;
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

  public String getMainRole() {
    return mainRole;
  }

  public void setMainRole(String mainRole) {
    this.mainRole = mainRole;
  }
}

