package fr.ancyracademy.esportclash.shared.date;

import java.time.LocalDateTime;

public class FixedDateProvider implements DateProvider {
  public static LocalDateTime DATE = LocalDateTime.of(2023, 1, 1, 0, 0, 0);

  public FixedDateProvider() {
  }

  public FixedDateProvider(LocalDateTime date) {
    DATE = date;
  }

  @Override
  public LocalDateTime now() {
    return DATE;
  }

}
