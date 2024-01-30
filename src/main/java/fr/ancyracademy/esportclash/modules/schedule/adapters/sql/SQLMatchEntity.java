package fr.ancyracademy.esportclash.modules.schedule.adapters.sql;

import fr.ancyracademy.esportclash.modules.schedule.model.Moment;
import fr.ancyracademy.esportclash.modules.team.adapters.sql.SQLTeamEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "matches")
public class SQLMatchEntity {
  @Id
  private String id;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "first_id")
  private SQLTeamEntity first;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "second_id")
  private SQLTeamEntity second;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "schedule_day_id")
  private SQLScheduleDayEntity scheduleDay;

  @Enumerated(EnumType.STRING)
  private Moment moment;

  public SQLMatchEntity() {
  }

  public SQLMatchEntity(String id, SQLTeamEntity first, SQLTeamEntity second, SQLScheduleDayEntity scheduleDay, Moment moment) {
    this.id = id;
    this.first = first;
    this.second = second;
    this.scheduleDay = scheduleDay;
    this.moment = moment;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public SQLTeamEntity getFirst() {
    return first;
  }

  public void setFirst(SQLTeamEntity first) {
    this.first = first;
  }


  public SQLTeamEntity getSecond() {
    return second;
  }

  public void setSecond(SQLTeamEntity second) {
    this.second = second;
  }

  public Moment getMoment() {
    return moment;
  }

  public void setMoment(Moment moment) {
    this.moment = moment;
  }
}
