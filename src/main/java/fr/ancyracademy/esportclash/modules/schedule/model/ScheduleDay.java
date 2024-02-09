package fr.ancyracademy.esportclash.modules.schedule.model;

import fr.ancyracademy.esportclash.modules.team.model.Team;
import fr.ancyracademy.esportclash.shared.BaseEntity;
import org.antlr.v4.runtime.misc.Pair;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public class ScheduleDay extends BaseEntity {
  private final String id;

  private final LocalDate date;

  private final HashMap<Moment, Match> matches;

  public ScheduleDay(String id, LocalDate date) {
    this.id = id;
    this.date = date;
    this.matches = new HashMap<>();
  }

  public ScheduleDay(ScheduleDay other) {
    this.id = other.id;
    this.date = other.date;
    this.matches = new HashMap<>(other.matches);
  }

  /**
   * Schedules the match at the given moment
   *
   * @param moment
   * @param match
   */
  public void schedule(Moment moment, Match match) {
    // A team can only play once per day
    if (teamPlays(match.getFirst())) {
      throw new IllegalStateException(
          "Team " + match.getFirst().getName() + " is already playing that day"
      );
    }

    if (teamPlays(match.getSecond())) {
      throw new IllegalStateException(
          "Team " + match.getSecond().getName() + " is already playing that day"
      );
    }

    matches.put(moment, match);
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

  public LocalDate getDate() {
    return date;
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
