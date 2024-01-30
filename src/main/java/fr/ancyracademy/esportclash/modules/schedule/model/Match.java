package fr.ancyracademy.esportclash.modules.schedule.model;

import fr.ancyracademy.esportclash.modules.team.model.Team;

import java.util.Objects;

public class Match {
  private final Team first;
  private final Team second;
  private String id;

  public Match(String id, Team first, Team second) {
    // Match implements the AlwaysValid pattern
    if (first == null || second == null) {
      throw new IllegalArgumentException("Teams must be provided");
    }

    if (!first.isComplete() || !second.isComplete()) {
      throw new IllegalStateException("Teams must be complete to be part of a match");
    }

    if (Objects.equals(first.getId(), second.getId())) {
      throw new IllegalStateException("Teams must be different");
    }

    this.id = id;
    this.first = first;
    this.second = second;
  }

  public Match(Match other) {
    this.id = other.id;
    this.first = new Team(other.first);
    this.second = new Team(other.second);
  }

  /**
   * Check if the team plays in this match
   *
   * @param team
   * @return
   */
  public boolean includesTeam(Team team) {
    return first.getId().equals(team.getId()) || second.getId().equals(team.getId());
  }

  public Team getFirst() {
    return first;
  }

  public Team getSecond() {
    return second;
  }

  public String getId() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Match match = (Match) o;
    return Objects.equals(first, match.first) && Objects.equals(second, match.second);
  }

  @Override
  public int hashCode() {
    return Objects.hash(first, second);
  }
}
