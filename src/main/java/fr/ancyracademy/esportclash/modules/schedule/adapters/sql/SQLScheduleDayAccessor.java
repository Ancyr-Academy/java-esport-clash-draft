package fr.ancyracademy.esportclash.modules.schedule.adapters.sql;

import fr.ancyracademy.esportclash.modules.schedule.model.ScheduleDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface SQLScheduleDayAccessor extends JpaRepository<ScheduleDay, String> {
  @Query("SELECT s FROM ScheduleDay s JOIN s.matches m WHERE m.id = :matchId")
  Optional<ScheduleDay> findByMatchId(@Param("matchId") String matchId);

  @Query("SELECT s FROM ScheduleDay s WHERE s.day = :day")
  Optional<ScheduleDay> findByDay(@Param("day") LocalDate day);
}
