package fr.ancyracademy.esportclash.modules.schedule.model;

import java.util.HashMap;
import java.util.Optional;

public class ScheduleDay {
  private HashMap<Moment, Match> matches = new HashMap();

  public void schedule(Moment moment, Match match) {
    // A team can play only once per day
  }

  public Optional<Match> getMatch(Moment moment) {
    return Optional.of(matches.get(moment));
  }

  enum Moment {
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
        default -> throw new IllegalArgumentException("Invalid moment");
      };
    }
  }
}
