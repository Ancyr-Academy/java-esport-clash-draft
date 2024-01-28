package fr.ancyracademy.esportclash.modules.team.adapters.sql;

import fr.ancyracademy.esportclash.modules.team.model.Team;
import fr.ancyracademy.esportclash.modules.team.ports.TeamRepository;

import java.util.ArrayList;
import java.util.Optional;

public class SQLTeamRepository implements TeamRepository {
  private final SQLTeamDataAccessor dataAccessor;

  public SQLTeamRepository(SQLTeamDataAccessor dataAccessor) {
    this.dataAccessor = dataAccessor;
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
    var sqlTeam = new SQLTeamEntity(team.getId(), team.getName(), new ArrayList<>());

    for (var member : team.getMembers()) {
      sqlTeam.addMember(
          new SQLTeamMemberEntity(
              new SQLTeamMemberId(member.getId(), team.getId()),
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
