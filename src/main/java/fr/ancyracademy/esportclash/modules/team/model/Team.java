package fr.ancyracademy.esportclash.modules.team.model;

import fr.ancyracademy.esportclash.modules.player.model.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Team {
  private final UUID id;

  private final String name;

  private final List<TeamMember> members;

  public Team(UUID id, String name, List<TeamMember> members) {
    this.id = id;
    this.name = name;
    this.members = members;
  }

  public Team(Team other) {
    this.id = other.id;
    this.name = other.name;
    this.members = new ArrayList<>(other.members.stream().map(TeamMember::new).toList());
  }

  public void join(UUID playerId, Role role) {
    if (members.stream().anyMatch(member -> member.getId().equals(playerId))) {
      throw new IllegalArgumentException("Player already in team");
    }

    if (members.stream().anyMatch(member -> member.getRole().equals(role))) {
      throw new IllegalArgumentException("Role already taken");
    }

    members.add(new TeamMember(playerId, role));
  }

  public void leave(UUID playerId) {
    if (members.stream().noneMatch(member -> member.getId().equals(playerId))) {
      throw new IllegalArgumentException("Player not in team");
    }

    members.removeIf(member -> member.getId().equals(playerId));
  }

  public boolean isComplete() {
    return members.size() == 5;
  }

  public boolean hasPlayer(UUID playerId) {
    return members.stream().anyMatch(member -> member.getId().equals(playerId));
  }

  public UUID getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public List<TeamMember> getMembers() {
    return members;
  }

  public static class TeamMember {
    private final UUID id;

    private Role role;

    public TeamMember(UUID id, Role role) {
      this.id = id;
      this.role = role;
    }

    public TeamMember(TeamMember other) {
      this.id = other.id;
      this.role = other.role;
    }

    public void moveTo(Role role) {
      this.role = role;
    }

    public Role getRole() {
      return role;
    }

    public UUID getId() {
      return id;
    }
  }
}
