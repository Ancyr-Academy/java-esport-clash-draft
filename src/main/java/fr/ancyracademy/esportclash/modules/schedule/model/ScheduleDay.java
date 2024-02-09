package fr.ancyracademy.esportclash.modules.schedule.model;

import fr.ancyracademy.esportclash.modules.team.model.Team;
import fr.ancyracademy.esportclash.shared.BaseEntity;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.Pair;

import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "schedule_days")
public class ScheduleDay extends BaseEntity {
  @Id
  private String id;

  @Column(name = "day")
  private LocalDate day;

  @OneToMany(
      mappedBy = "scheduleDay",
      cascade = CascadeType.ALL,
      orphanRemoval = true,
      fetch = FetchType.EAGER
  )
  @MapKeyEnumerated(EnumType.STRING)
  private Map<Moment, Match> matches;

  public ScheduleDay() {
  }

  public ScheduleDay(String id, LocalDate date) {
    this.id = id;
    this.day = date;
    this.matches = new HashMap<>();
  }

  public ScheduleDay(ScheduleDay other) {
    this.id = other.id;
    this.day = other.day;
    this.matches = new HashMap<>(other.matches);
  }

  public Match schedule(Moment moment, Team first, Team second) {
    // A team can only play once per day
    if (teamPlays(first)) {
      throw new IllegalStateException(
          "Team " + first.getName() + " is already playing that day"
      );
    }

    if (teamPlays(second)) {
      throw new IllegalStateException(
          "Team " + second.getName() + " is already playing that day"
      );
    }

    var match = new Match(
        UUID.randomUUID().toString(),
        this.id,
        first,
        second
    );

    matches.put(moment, match);

    return match;
  }

  public void cancel(String matchId) {
    var match = findMatch(matchId);
    if (match.isEmpty()) {
      return;
    }

    matches.remove(match.get().a);
  }

  public Optional<Match> getMatch(Moment moment) {
    return Optional.of(matches.get(moment));
  }

  public LocalDate getDay() {
    return day;
  }

  public String getId() {
    return id;
  }

  public boolean containsMatch(String matchId) {
    return findMatch(matchId).isPresent();
  }

  public Optional<Pair<Moment, Match>> findMatch(String matchId) {
    return matches.keySet().stream()
        .filter(m -> matches.get(m).getId().equals(matchId))
        .findFirst()
        .map(m -> new Pair<>(m, matches.get(m)));
  }

  public Optional<Pair<Moment, Match>> findMatch(Match match) {
    return findMatch(match.getId());
  }

  public boolean isEmpty() {
    return matches.isEmpty();
  }

  public List<Map.Entry<Moment, Match>> getMatches() {
    return List.copyOf(matches.entrySet());
  }

  private boolean teamPlays(Team team) {
    return matches.values().stream().anyMatch(m -> m.includesTeam(team));
  }
}
