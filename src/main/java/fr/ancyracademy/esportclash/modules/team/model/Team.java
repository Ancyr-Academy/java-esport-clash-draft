package fr.ancyracademy.esportclash.modules.team.model;

import fr.ancyracademy.esportclash.modules.player.model.Player;
import fr.ancyracademy.esportclash.modules.player.model.Role;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "teams")
public class Team {
  @Id
  private String id;

  @Column
  private String name;

  @OneToMany(
      mappedBy = "team",
      cascade = CascadeType.ALL,
      orphanRemoval = true,
      fetch = FetchType.EAGER
  )
  private Set<TeamMember> members;

  protected Team() {

  }

  public Team(String id, String name) {
    this.id = id;
    this.name = name;
    this.members = new HashSet<>();
  }

  public Team(String id, String name, Set<TeamMember> members) {
    this.id = id;
    this.name = name;
    this.members = members;
  }

  public Team(Team other) {
    this.id = other.id;
    this.name = other.name;
    this.members = other.members.stream().map(TeamMember::new).collect(Collectors.toSet());
  }

  public void join(String playerId, Role role) {
    if (members.stream().anyMatch(member -> member.getPlayerId().equals(playerId))) {
      throw new IllegalArgumentException("Player already in team");
    }

    if (members.stream().anyMatch(member -> member.getRole().equals(role))) {
      throw new IllegalArgumentException("Role already taken");
    }

    members.add(new TeamMember(
        UUID.randomUUID().toString(),
        playerId,
        id,
        role
    ));
  }

  public void leave(String playerId) {
    if (members.stream().noneMatch(member -> member.getPlayerId().equals(playerId))) {
      throw new IllegalArgumentException("Player not in team");
    }

    members.removeIf(member -> member.getPlayerId().equals(playerId));
  }

  public boolean isComplete() {
    return members.size() == 5;
  }

  public boolean hasPlayer(String playerId) {
    return members.stream().anyMatch(member -> member.getPlayerId().equals(playerId));
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Set<TeamMember> getMembers() {
    return members;
  }

  @Entity
  @Table(name = "team_members")
  public static class TeamMember {
    @Id
    private String id;

    @Column(name = "player_id")
    private String playerId;

    @Column(name = "team_id")
    private String teamId;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("teamId")
    @JoinColumn(name = "team_id", insertable = false, updatable = false)
    private Team team;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("playerId")
    @JoinColumn(name = "player_id", insertable = false, updatable = false)
    private Player player;

    @Enumerated(EnumType.STRING)
    private Role role;

    protected TeamMember() {

    }

    public TeamMember(String id, String playerId, String teamId, Role role) {
      this.id = id;
      this.playerId = playerId;
      this.teamId = teamId;
      this.role = role;
    }

    public TeamMember(TeamMember other) {
      this.playerId = other.playerId;
      this.teamId = other.teamId;
      this.role = other.role;
    }

    public void moveTo(Role role) {
      this.role = role;
    }

    public Role getRole() {
      return role;
    }

    public String getId() {
      return id;
    }

    public String getPlayerId() {
      return playerId;
    }

    public String getTeamId() {
      return teamId;
    }
  }
}
