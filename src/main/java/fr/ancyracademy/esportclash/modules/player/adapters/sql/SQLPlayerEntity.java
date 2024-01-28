package fr.ancyracademy.esportclash.modules.player.adapters.sql;

import fr.ancyracademy.esportclash.modules.player.model.Role;
import jakarta.persistence.*;

@Entity
@Table(name = "players")
public class SQLPlayerEntity {
  @Id
  private String id;

  @Column
  private String name;

  @Enumerated(EnumType.STRING)
  @Column
  private Role mainRole;

  public SQLPlayerEntity() {
  }

  public SQLPlayerEntity(String id, String name, Role mainRole) {
    this.id = id;
    this.name = name;
    this.mainRole = mainRole;
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

