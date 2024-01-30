package fr.ancyracademy.esportclash.modules.schedule.adapters.sql;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "schedule_days")
public class SQLScheduleDayEntity {
  @Id
  private String id;

  @Column(name = "day")
  private LocalDate day;

  @OneToMany(
      mappedBy = "scheduleDay",
      cascade = CascadeType.ALL,
      orphanRemoval = true,
      fetch = FetchType.EAGER
  )
  private Set<SQLMatchEntity> matches;

  public SQLScheduleDayEntity() {
  }

  public SQLScheduleDayEntity(String id, LocalDate day) {
    this.id = id;
    this.day = day;
    this.matches = new HashSet<>();
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public LocalDate getDay() {
    return day;
  }

  public void setDay(LocalDate day) {
    this.day = day;
  }

  public Set<SQLMatchEntity> getMatches() {
    return matches;
  }

  public void setMatches(Set<SQLMatchEntity> matches) {
    this.matches = matches;
  }

  public void addMatch(SQLMatchEntity match) {
    this.matches.add(match);
  }

}
