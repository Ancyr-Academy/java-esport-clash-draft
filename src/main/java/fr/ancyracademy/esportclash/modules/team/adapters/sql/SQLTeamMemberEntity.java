package fr.ancyracademy.esportclash.modules.team.adapters.sql;

import fr.ancyracademy.esportclash.modules.player.model.Role;
import jakarta.persistence.*;

@Entity
@Table(name = "team_members")
public class SQLTeamMemberEntity {
  @EmbeddedId
  private SQLTeamMemberId id;

  @Enumerated(EnumType.STRING)
  private Role role;

  public SQLTeamMemberEntity() {
  }

  public SQLTeamMemberEntity(SQLTeamMemberId id, Role role) {
    this.id = id;
    this.role = role;
  }

  public SQLTeamMemberId getId() {
    return id;
  }

  public Role getRole() {
    return role;
  }
}
