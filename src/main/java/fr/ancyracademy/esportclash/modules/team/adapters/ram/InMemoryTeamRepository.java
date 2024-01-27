package fr.ancyracademy.esportclash.modules.team.adapters.ram;

import fr.ancyracademy.esportclash.modules.team.model.Team;
import fr.ancyracademy.esportclash.modules.team.ports.TeamRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class InMemoryTeamRepository implements TeamRepository {
  private final Map<UUID, Team> teams = new HashMap<>();

  @Override
  public Optional<Team> findById(UUID id) {
    return teams.values().stream()
        .filter(team -> team.getId().equals(id))
        .findFirst()
        .map(Team::new);
  }

  @Override
  public Optional<Team> findByPlayerId(UUID playerId) {
    return teams.values().stream()
        .filter(team -> team.hasPlayer(playerId))
        .findFirst()
        .map(Team::new);
  }

  @Override
  public void save(Team team) {
    teams.put(team.getId(), team);
  }

  @Override
  public void delete(Team team) {
    teams.remove(team.getId());
  }

  @Override
  public void clear() {
    teams.clear();
  }
}
