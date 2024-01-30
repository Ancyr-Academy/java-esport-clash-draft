package fr.ancyracademy.esportclash.modules.team.model;

import fr.ancyracademy.esportclash.modules.player.model.Role;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Team {
  private final String id;

  private final String name;

  private final Set<TeamMember> members;

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
    if (members.stream().anyMatch(member -> member.getId().equals(playerId))) {
      throw new IllegalArgumentException("Player already in team");
    }

    if (members.stream().anyMatch(member -> member.getRole().equals(role))) {
      throw new IllegalArgumentException("Role already taken");
    }

    members.add(new TeamMember(playerId, role));
  }

  public void leave(String playerId) {
    if (members.stream().noneMatch(member -> member.getId().equals(playerId))) {
      throw new IllegalArgumentException("Player not in team");
    }

    members.removeIf(member -> member.getId().equals(playerId));
  }

  public boolean isComplete() {
    return members.size() == 5;
  }

  public boolean hasPlayer(String playerId) {
    return members.stream().anyMatch(member -> member.getId().equals(playerId));
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

  public static class TeamMember {
    private final String id;

    private Role role;

    public TeamMember(String id, Role role) {
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

    public String getId() {
      return id;
    }
  }
}
