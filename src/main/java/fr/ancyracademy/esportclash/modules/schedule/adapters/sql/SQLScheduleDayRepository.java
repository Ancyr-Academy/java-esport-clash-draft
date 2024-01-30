package fr.ancyracademy.esportclash.modules.schedule.adapters.sql;

import fr.ancyracademy.esportclash.modules.schedule.model.Match;
import fr.ancyracademy.esportclash.modules.schedule.model.Moment;
import fr.ancyracademy.esportclash.modules.schedule.model.ScheduleDay;
import fr.ancyracademy.esportclash.modules.schedule.ports.ScheduleDayRepository;
import fr.ancyracademy.esportclash.modules.team.adapters.sql.SQLTeamEntity;
import fr.ancyracademy.esportclash.modules.team.model.Team;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class SQLScheduleDayRepository implements ScheduleDayRepository {
  private final SQLScheduleDayAccessor accessor;

  private final EntityManager entityManager;

  public SQLScheduleDayRepository(SQLScheduleDayAccessor accessor, EntityManager entityManager) {
    this.accessor = accessor;
    this.entityManager = entityManager;
  }

  @Override
  public Optional<ScheduleDay> findById(String id) {
    return accessor.findById(id).map(this::toDomain);
  }

  @Override
  public Optional<ScheduleDay> findByDate(LocalDate date) {
    return accessor.findByDay(date).map(this::toDomain);
  }

  @Override
  public Optional<ScheduleDay> findByMatchId(String matchId) {
    return accessor.findByMatchId(matchId).map(this::toDomain);
  }

  @Override
  public void save(ScheduleDay scheduleDay) {
    SQLScheduleDayEntity entity = new SQLScheduleDayEntity(scheduleDay.getId(), scheduleDay.getDate());

    for (Map.Entry<Moment, Match> entry : scheduleDay.getMatches()) {
      var moment = entry.getKey();
      var match = entry.getValue();

      var firstTeam = entityManager.find(SQLTeamEntity.class, match.getFirst().getId());
      var secondTeam = entityManager.find(SQLTeamEntity.class, match.getSecond().getId());

      SQLMatchEntity matchEntity = new SQLMatchEntity(
          match.getId(),
          firstTeam,
          secondTeam,
          entity,
          moment
      );

      entity.addMatch(matchEntity);
    }

    accessor.save(entity);
  }

  @Override
  public void delete(ScheduleDay scheduleDay) {
    accessor.deleteById(scheduleDay.getId());
  }

  @Override
  public void clear() {
    accessor.deleteAll();
  }

  private ScheduleDay toDomain(SQLScheduleDayEntity entity) {
    ScheduleDay scheduleDay = new ScheduleDay(entity.getId(), entity.getDay());

    entity.getMatches().forEach(matchEntity -> {
      var first = toDomain(matchEntity.getFirst());
      var second = toDomain(matchEntity.getSecond());
      var match = new Match(matchEntity.getId(), first, second);

      scheduleDay.schedule(matchEntity.getMoment(), match);
    });

    return scheduleDay;
  }

  private Team toDomain(SQLTeamEntity entity) {
    return new Team(
        entity.getId(),
        entity.getName(),
        entity.getMembers().stream().map(member ->
                new Team.TeamMember(
                    member.getPlayerId(),
                    member.getRole()))
            .collect(Collectors.toSet())
    );
  }
}
