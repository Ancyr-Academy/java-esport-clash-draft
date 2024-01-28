package fr.ancyracademy.esportclash.modules.team.ports;

import fr.ancyracademy.esportclash.modules.team.model.Team;

import java.util.Optional;

public interface TeamRepository {
  Optional<Team> findById(String id);

  Optional<Team> findByPlayerId(String playerId);

  void save(Team team);

  void delete(Team team);

  void clear();
}
