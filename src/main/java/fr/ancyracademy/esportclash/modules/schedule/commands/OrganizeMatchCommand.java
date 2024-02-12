package fr.ancyracademy.esportclash.modules.schedule.commands;

import an.awesome.pipelinr.Command;
import fr.ancyracademy.esportclash.modules.schedule.model.Moment;
import fr.ancyracademy.esportclash.shared.IdResponse;

import java.time.LocalDate;

public class OrganizeMatchCommand implements Command<IdResponse> {
  private final LocalDate date;

  private final String firstTeamId;

  private final String secondTeamId;

  private final Moment moment;


  public OrganizeMatchCommand(LocalDate date, String firstTeamId, String secondTeamId, Moment moment) {
    this.date = date;
    this.firstTeamId = firstTeamId;
    this.secondTeamId = secondTeamId;
    this.moment = moment;
  }

  public LocalDate getDate() {
    return date;
  }


  public String getFirstTeamId() {
    return firstTeamId;
  }


  public String getSecondTeamId() {
    return secondTeamId;
  }


  public Moment getMoment() {
    return moment;
  }

}
