package fr.ancyracademy.javaesportclash.modules.player.adapters.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document("players")
public class MongoPlayerDocument {
  @Id
  private UUID id;

  private String name;

  private String mainRole;

  public MongoPlayerDocument() {
  }

  public MongoPlayerDocument(UUID id, String name, String mainRole) {
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

