package fr.ancyracademy.esportclash.modules.schedule.model;

import fr.ancyracademy.esportclash.modules.team.model.Team;

import java.util.Objects;

public class Match {
  private final Team a;

  private final Team b;

  public Match(Team first, Team second) {
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

    this.a = first;
    this.b = second;
  }

  public Match(Match other) {
    this.a = new Team(other.a);
    this.b = new Team(other.b);
  }

  /**
   * Check if the team plays in this match
   *
   * @param team
   * @return
   */
  public boolean includesTeam(Team team) {
    return a.getId().equals(team.getId()) || b.getId().equals(team.getId());
  }

  public Team getA() {
    return a;
  }

  public Team getB() {
    return b;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Match match = (Match) o;
    return Objects.equals(a, match.a) && Objects.equals(b, match.b);
  }

  @Override
  public int hashCode() {
    return Objects.hash(a, b);
  }
}
