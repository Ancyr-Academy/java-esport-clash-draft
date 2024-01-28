package fr.ancyracademy.esportclash.modules.schedule.model;

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