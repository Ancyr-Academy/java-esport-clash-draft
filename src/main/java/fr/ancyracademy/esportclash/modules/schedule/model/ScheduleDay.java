package fr.ancyracademy.esportclash.modules.schedule.model;

import fr.ancyracademy.esportclash.modules.team.model.Team;
import fr.ancyracademy.esportclash.shared.Entity;
import org.antlr.v4.runtime.misc.Pair;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ScheduleDay extends Entity {
  private final String id;
  private final LocalDate date;
  private final Schedule schedule;

  public ScheduleDay(String id, LocalDate date) {
    this.id = id;
    this.date = date;
    this.schedule = new Schedule();
  }

  public ScheduleDay(ScheduleDay other) {
    this.id = other.id;
    this.date = other.date;
    this.schedule = new Schedule(other.schedule);
  }

  /**
   * Schedules the match at the given moment
   *
   * @param moment
   * @param match
   */
  public void schedule(Moment moment, Match match) {
    // First check if the match isn't already scheduled
    var scheduledMatch = schedule.findMatch(match);

    if (scheduledMatch.isPresent()) {
      // If the match is scheduled at the same moment, we consider it a success
      if (scheduledMatch.get().a.equals(moment)) {
        return;
      }

      throw new IllegalStateException("Match already scheduled");
    }

    // A team can only play once per day
    if (schedule.teamPlays(match.getFirst())) {
      throw new IllegalStateException(
          "Team " + match.getFirst().getName() + " is already playing that day"
      );
    }

    if (schedule.teamPlays(match.getSecond())) {
      throw new IllegalStateException(
          "Team " + match.getSecond().getName() + " is already playing that day"
      );
    }

    schedule.put(moment, match);
  }

  public void cancel(String matchId) {
    schedule.remove(matchId);
  }

  public Optional<Match> getMatch(Moment moment) {
    return Optional.of(schedule.get(moment));
  }

  public LocalDate getDate() {
    return date;
  }

  public String getId() {
    return id;
  }

  public boolean containsMatch(String matchId) {
    return schedule.findMatch(matchId).isPresent();
  }

  public boolean isEmpty() {
    return schedule.isEmpty();
  }

  public List<Map.Entry<Moment, Match>> getMatches() {
    return schedule.getMatches();
  }

  /**
   * Represents the schedule of a day
   */
  public static class Schedule {
    private final HashMap<Moment, Match> matches;

    public Schedule() {
      this.matches = new HashMap<>();
    }

    public Schedule(Schedule other) {
      this.matches = new HashMap<>();

      for (var moment : other.matches.keySet()) {
        this.matches.put(moment, new Match(other.matches.get(moment)));
      }
    }

    /**
     * Check if a team is already playing that day
     *
     * @param team
     * @return
     */
    public boolean teamPlays(Team team) {
      return matches.values().stream().anyMatch(m -> m.includesTeam(team));
    }

    /**
     * Find a match in the schedule
     *
     * @param matchId
     * @return
     */
    public Optional<Pair<Moment, Match>> findMatch(String matchId) {
      return matches.keySet().stream()
          .filter(m -> matches.get(m).getId().equals(matchId))
          .findFirst()
          .map(m -> new Pair<>(m, matches.get(m)));
    }

    public Optional<Pair<Moment, Match>> findMatch(Match match) {
      return findMatch(match.getId());
    }

    public Match get(Moment moment) {
      return matches.get(moment);
    }

    public void put(Moment moment, Match match) {
      matches.put(moment, match);
    }

    public void remove(String matchId) {
      matches.values().removeIf(m -> m.getId().equals(matchId));
    }

    public boolean isEmpty() {
      return matches.isEmpty();
    }

    public List<Map.Entry<Moment, Match>> getMatches() {
      return List.copyOf(matches.entrySet());
    }
  }
}
