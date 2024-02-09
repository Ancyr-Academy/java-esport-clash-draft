package fr.ancyracademy.esportclash.modules.team.adapters.sql;

import fr.ancyracademy.esportclash.modules.team.model.Team;
import fr.ancyracademy.esportclash.modules.team.ports.TeamRepository;

import java.util.Optional;

public class SQLTeamRepository implements TeamRepository {
  private final SQLTeamDataAccessor dataAccessor;

  public SQLTeamRepository(SQLTeamDataAccessor dataAccessor) {
    this.dataAccessor = dataAccessor;
  }

  @Override
  public Optional<Team> findById(String id) {
    return dataAccessor.findById(id);
  }

  @Override
  public Optional<Team> findByPlayerId(String playerId) {
    return dataAccessor.findByPlayerId(playerId);
  }

  @Override
  public void save(Team team) {
    dataAccessor.save(team);
  }

  @Override
  public void delete(Team team) {
    dataAccessor.deleteById(team.getId());
  }

  @Override
  public void clear() {
    dataAccessor.deleteAll();
  }
}
