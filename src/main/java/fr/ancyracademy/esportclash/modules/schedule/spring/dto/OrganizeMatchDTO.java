package fr.ancyracademy.esportclash.modules.schedule.spring.dto;

import fr.ancyracademy.esportclash.modules.schedule.model.Moment;

import java.time.LocalDate;

public class OrganizeMatchDTO {
  private LocalDate date;

  private String firstTeamId;

  private String secondTeamId;

  private Moment moment;

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public void setDate(String date) {
    this.date = LocalDate.parse(date);
  }

  public String getFirstTeamId() {
    return firstTeamId;
  }

  public void setFirstTeamId(String firstTeamId) {
    this.firstTeamId = firstTeamId;
  }

  public String getSecondTeamId() {
    return secondTeamId;
  }

  public void setSecondTeamId(String secondTeamId) {
    this.secondTeamId = secondTeamId;
  }

  public Moment getMoment() {
    return moment;
  }

  public void setMoment(Moment moment) {
    this.moment = moment;
  }

  public void setMoment(String moment) {
    this.moment = Moment.fromString(moment);
  }
}
