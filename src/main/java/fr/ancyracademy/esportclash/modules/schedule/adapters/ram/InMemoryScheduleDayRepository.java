package fr.ancyracademy.esportclash.modules.schedule.adapters.ram;

import fr.ancyracademy.esportclash.modules.schedule.model.ScheduleDay;
import fr.ancyracademy.esportclash.modules.schedule.ports.ScheduleDayRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryScheduleDayRepository implements ScheduleDayRepository {
  private final Map<LocalDate, ScheduleDay> scheduleDays = new HashMap<>();

  @Override
  public Optional<ScheduleDay> findById(String id) {
    return scheduleDays.values().stream()
        .filter(scheduleDay -> scheduleDay.getId().equals(id))
        .findFirst()
        .map(ScheduleDay::new);
  }

  @Override
  public Optional<ScheduleDay> findByDate(LocalDate date) {
    return scheduleDays.values().stream()
        .filter(scheduleDay -> scheduleDay.getDay().equals(date))
        .findFirst()
        .map(ScheduleDay::new);
  }

  @Override
  public Optional<ScheduleDay> findByMatchId(String matchId) {
    return scheduleDays.values().stream()
        .filter(scheduleDay -> scheduleDay.containsMatch(matchId))
        .findFirst()
        .map(ScheduleDay::new);
  }

  @Override
  public void save(ScheduleDay scheduleDay) {
    scheduleDays.put(scheduleDay.getDay(), scheduleDay);
  }

  @Override
  public void delete(ScheduleDay scheduleDay) {
    scheduleDays.remove(scheduleDay.getDay());
  }

  @Override
  public void clear() {
    scheduleDays.clear();
  }
}
