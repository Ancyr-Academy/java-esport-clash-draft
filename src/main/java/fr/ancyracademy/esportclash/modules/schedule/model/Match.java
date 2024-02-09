package fr.ancyracademy.esportclash.modules.schedule.model;

import fr.ancyracademy.esportclash.modules.team.model.Team;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "matches")
public class Match {
  @Id
  private String id;

  @Column(name = "first_id")
  private String firstId;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "first_id")
  @MapsId("firstId")
  private Team first;

  @Column(name = "second_id")
  private String secondId;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "second_id")
  @MapsId("secondId")
  private Team second;

  @Column(name = "schedule_day_id")
  private String scheduleDayId;

  @ManyToOne()
  @JoinColumn(name = "schedule_day_id")
  @MapsId("scheduleDayId")
  private ScheduleDay scheduleDay;

  public Match() {
  }

  public Match(String id, String scheduleDayId, Team first, Team second) {
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
    this.scheduleDayId = scheduleDayId;
    this.first = first;
    this.firstId = first.getId();
    this.second = second;
    this.secondId = second.getId();
  }

  public boolean includesTeam(Team team) {
    return first.getId().equals(team.getId()) || second.getId().equals(team.getId());
  }

  public Team getFirst() {
    return first;
  }

  public String getFirstId() {
    return firstId;
  }

  public Team getSecond() {
    return second;
  }

  public String getSecondId() {
    return secondId;
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
