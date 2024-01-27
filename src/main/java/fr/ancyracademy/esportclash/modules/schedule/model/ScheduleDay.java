package fr.ancyracademy.esportclash.modules.schedule.model;

import fr.ancyracademy.esportclash.modules.team.model.Team;
import org.antlr.v4.runtime.misc.Pair;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Optional;

public class ScheduleDay {
  private final LocalDate date;
  private final Schedule schedule = new Schedule();

  public ScheduleDay(LocalDate date) {
    this.date = date;
  }

  public void schedule(Moment moment, Match match) {
    var scheduledMatch = schedule.findMatch(match);

    if (scheduledMatch.isPresent()) {
      if (scheduledMatch.get().a.equals(moment)) {
        return;
      }

      throw new IllegalStateException("Match already scheduled");
    }

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

  public Optional<Match> getMatch(Moment moment) {
    return Optional.of(schedule.get(moment));
  }


  public enum Moment {
    MORNING,
    AFTERNOON;

    public static Moment fromString(String moment) {
      return switch (moment) {
        case "MORNING" -> Moment.MORNING;
        case "AFTERNOON" -> Moment.AFTERNOON;
        default ->
            throw new IllegalArgumentException("Invalid moment: " + moment);
      };
    }

    @Override
    public String toString() {
      return switch (this) {
        case MORNING -> "MORNING";
        case AFTERNOON -> "AFTERNOON";
      };
    }
  }

  public static class Schedule {
    private final HashMap<Moment, Match> matches = new HashMap<>();

    public boolean teamPlays(Team team) {
      return matches.values().stream().anyMatch(m -> m.includesTeam(team));
    }

    public Optional<Pair<Moment, Match>> findMatch(Match match) {
      return matches.keySet().stream()
          .filter(m -> matches.get(m).equals(match))
          .findFirst()
          .map(m -> new Pair<>(m, matches.get(m)));
    }

    public Match get(Moment moment) {
      return matches.get(moment);
    }

    public void put(Moment moment, Match match) {
      matches.put(moment, match);
    }

  }
}
