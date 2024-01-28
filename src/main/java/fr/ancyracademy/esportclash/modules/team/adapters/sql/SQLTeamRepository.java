package fr.ancyracademy.esportclash.modules.team.adapters.sql;

import fr.ancyracademy.esportclash.modules.player.adapters.sql.SQLPlayerEntity;
import fr.ancyracademy.esportclash.modules.team.model.Team;
import fr.ancyracademy.esportclash.modules.team.ports.TeamRepository;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;
import java.util.Optional;

public class SQLTeamRepository implements TeamRepository {
  private final SQLTeamDataAccessor dataAccessor;

  private final EntityManager entityManager;

  public SQLTeamRepository(SQLTeamDataAccessor dataAccessor, EntityManager entityManager) {
    this.dataAccessor = dataAccessor;
    this.entityManager = entityManager;
  }

  @Override
  public Optional<Team> findById(String id) {
    var sqlTeam = dataAccessor.findById(id);
    return sqlTeam.map(this::fromSql);
  }

  @Override
  public Optional<Team> findByPlayerId(String playerId) {
    var sqlTeam = dataAccessor.findByPlayerId(playerId);
    return sqlTeam.map(this::fromSql);
  }

  @Override
  public void save(Team team) {
    var sqlTeam = new SQLTeamEntity(team.getId(), team.getName());

    for (var member : team.getMembers()) {
      var sqlPlayer = entityManager.getReference(SQLPlayerEntity.class, member.getId());

      sqlTeam.addMember(
          new SQLTeamMemberEntity(
              sqlTeam,
              sqlPlayer,
              member.getRole()
          )
      );
    }

    dataAccessor.save(sqlTeam);
  }

  @Override
  public void delete(Team team) {
    dataAccessor.deleteById(team.getId());
  }

  @Override
  public void clear() {
    dataAccessor.deleteAll();
  }

  private Team fromSql(SQLTeamEntity sqlTeam) {
    var members = new ArrayList<Team.TeamMember>();
    for (var member : sqlTeam.getMembers()) {
      members.add(
          new Team.TeamMember(
              member.getId().getPlayerId(),
              member.getRole()
          )
      );
    }

    return new Team(sqlTeam.getId(), sqlTeam.getName(), members);
  }
}
