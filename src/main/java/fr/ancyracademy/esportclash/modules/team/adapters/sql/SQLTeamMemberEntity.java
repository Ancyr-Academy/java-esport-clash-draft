package fr.ancyracademy.esportclash.modules.team.adapters.sql;

import fr.ancyracademy.esportclash.modules.player.adapters.sql.SQLPlayerEntity;
import fr.ancyracademy.esportclash.modules.player.model.Role;
import jakarta.persistence.*;

@Entity
@Table(name = "team_members")
public class SQLTeamMemberEntity {
  @EmbeddedId
  private SQLTeamMemberId id;

  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("teamId") // Maps teamId in TeamMemberId
  @JoinColumn(name = "team_id", insertable = false, updatable = false)
  private SQLTeamEntity team;

  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("playerId") // Maps playerId in TeamMemberId
  @JoinColumn(name = "player_id", insertable = false, updatable = false)
  private SQLPlayerEntity player;

  @Enumerated(EnumType.STRING)
  private Role role;

  public SQLTeamMemberEntity() {
  }

  public SQLTeamMemberEntity(
      SQLTeamEntity team,
      SQLPlayerEntity player,
      Role role) {
    this.id = new SQLTeamMemberId(player.getId(), team.getId());
    this.team = team;
    this.player = player;
    this.role = role;
  }

  public SQLTeamMemberId getId() {
    return id;
  }

  public Role getRole() {
    return role;
  }
}
