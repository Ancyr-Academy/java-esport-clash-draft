package fr.ancyracademy.javaesportclash.modules.team.ports;

import fr.ancyracademy.javaesportclash.modules.team.model.Team;

import java.util.Optional;
import java.util.UUID;

public interface TeamRepository {
  Optional<Team> findById(UUID id);

  Optional<Team> findByPlayerId(UUID playerId);

  void save(Team player);

  void delete(Team player);

  void clear();
}
