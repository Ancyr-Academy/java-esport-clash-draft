package fr.ancyracademy.esportclash.modules.schedule.adapters.sql;

import fr.ancyracademy.esportclash.modules.schedule.model.ScheduleDay;
import fr.ancyracademy.esportclash.modules.schedule.ports.ScheduleDayRepository;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.Optional;

public class SQLScheduleDayRepository implements ScheduleDayRepository {
  private final SQLScheduleDayAccessor accessor;
  private final EntityManager entityManager;

  public SQLScheduleDayRepository(SQLScheduleDayAccessor accessor, EntityManager entityManager) {
    this.accessor = accessor;
    this.entityManager = entityManager;
  }

  @Override
  public Optional<ScheduleDay> findById(String id) {
    return accessor.findById(id);
  }

  @Override
  public Optional<ScheduleDay> findByDate(LocalDate date) {
    return accessor.findByDay(date);
  }

  @Override
  public Optional<ScheduleDay> findByMatchId(String matchId) {
    return accessor.findByMatchId(matchId);
  }

  @Override
  public void save(ScheduleDay scheduleDay) {
    accessor.save(scheduleDay);
  }

  @Override
  public void delete(ScheduleDay scheduleDay) {
    accessor.deleteById(scheduleDay.getId());
  }

  @Override
  public void clear() {
    accessor.deleteAll();
  }
}
