package fr.ancyracademy.esportclash.modules.schedule.adapters.sql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface SQLScheduleDayAccessor extends JpaRepository<SQLScheduleDayEntity, String> {
  @Query("SELECT s FROM SQLScheduleDayEntity s JOIN s.matches m WHERE m.id = :matchId")
  Optional<SQLScheduleDayEntity> findByMatchId(@Param("matchId") String matchId);

  @Query("SELECT s FROM SQLScheduleDayEntity s WHERE s.day = :day")
  Optional<SQLScheduleDayEntity> findByDay(@Param("day") LocalDate day);
}
