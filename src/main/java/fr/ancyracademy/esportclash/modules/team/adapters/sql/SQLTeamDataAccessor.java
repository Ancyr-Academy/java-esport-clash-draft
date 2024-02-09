package fr.ancyracademy.esportclash.modules.team.adapters.sql;

import fr.ancyracademy.esportclash.modules.team.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SQLTeamDataAccessor extends JpaRepository<Team, String> {
  @Query("SELECT t FROM Team t JOIN t.members m WHERE m.playerId = :playerId")
  Optional<Team> findByPlayerId(@Param("playerId") String playerId);
}
