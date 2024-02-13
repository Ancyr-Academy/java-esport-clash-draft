package fr.ancyracademy.esportclash.shared.date;

import java.time.LocalDateTime;

public class CurrentDateProvider implements DateProvider {
  @Override
  public LocalDateTime now() {
    return LocalDateTime.now();
  }
}
