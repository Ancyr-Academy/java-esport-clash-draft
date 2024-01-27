package fr.ancyracademy.esportclash.modules.schedule.model;

import fr.ancyracademy.esportclash.modules.team.model.Team;

import java.util.Objects;

public class Match {
  private final Team first;

  private final Team second;

  public Match(Team first, Team second) {
    if (first == null || second == null) {
      throw new IllegalArgumentException("Teams must be provided");
    }

    if (!first.isComplete() || !second.isComplete()) {
      throw new IllegalStateException("Teams must be complete to be part of a match");
    }

    if (Objects.equals(first.getId(), second.getId())) {
      throw new IllegalStateException("Teams must be different");
    }

    this.first = first;
    this.second = second;
  }

  public Team getFirst() {
    return first;
  }

  public Team getSecond() {
    return second;
  }
}
