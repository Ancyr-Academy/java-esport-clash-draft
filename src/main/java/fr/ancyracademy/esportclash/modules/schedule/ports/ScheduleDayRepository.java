package fr.ancyracademy.esportclash.modules.schedule.ports;

import fr.ancyracademy.esportclash.modules.schedule.model.ScheduleDay;

import java.time.LocalDate;
import java.util.Optional;

public interface ScheduleDayRepository {
  Optional<ScheduleDay> findById(String id);

  Optional<ScheduleDay> findByDate(LocalDate date);

  void save(ScheduleDay scheduleDay);

  void delete(ScheduleDay scheduleDay);

  void clear();
}
