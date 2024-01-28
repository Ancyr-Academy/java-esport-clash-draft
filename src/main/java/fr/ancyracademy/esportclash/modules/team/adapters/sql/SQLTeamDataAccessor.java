package fr.ancyracademy.esportclash.modules.team.adapters.sql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SQLTeamDataAccessor extends JpaRepository<SQLTeamEntity, String> {
  @Query("SELECT t FROM SQLTeamEntity t JOIN t.members m WHERE m.id.playerId = :playerId")
  Optional<SQLTeamEntity> findByPlayerId(@Param("playerId") String playerId);
}
