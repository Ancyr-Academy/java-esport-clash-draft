package fr.ancyracademy.esportclash.modules.team.adapters.sql;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SQLTeamMemberId implements Serializable {
  @Column(name = "player_id")
  private String playerId;

  @Column(name = "team_id")
  private String teamId;

  public SQLTeamMemberId() {
  }

  public SQLTeamMemberId(String playerId, String teamId) {
    this.playerId = playerId;
    this.teamId = teamId;
  }

  public String getPlayerId() {
    return playerId;
  }

  public String getTeamId() {
    return teamId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SQLTeamMemberId that = (SQLTeamMemberId) o;
    return Objects.equals(playerId, that.playerId) && Objects.equals(teamId, that.teamId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(playerId, teamId);
  }
}
