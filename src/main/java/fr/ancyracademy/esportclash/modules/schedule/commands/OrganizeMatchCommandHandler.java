package fr.ancyracademy.esportclash.modules.schedule.commands;

import an.awesome.pipelinr.Command;
import fr.ancyracademy.esportclash.modules.schedule.model.ScheduleDay;
import fr.ancyracademy.esportclash.modules.schedule.ports.ScheduleDayRepository;
import fr.ancyracademy.esportclash.modules.team.ports.TeamRepository;
import fr.ancyracademy.esportclash.shared.IdResponse;
import fr.ancyracademy.esportclash.shared.exceptions.NotFoundException;
import fr.ancyracademy.esportclash.shared.id.IdProvider;

public class OrganizeMatchCommandHandler implements Command.Handler<OrganizeMatchCommand, IdResponse> {
  private final IdProvider idProvider;
  private final ScheduleDayRepository scheduleDayRepository;
  private final TeamRepository teamRepository;

  public OrganizeMatchCommandHandler(
      IdProvider idProvider,
      ScheduleDayRepository scheduleDayRepository,
      TeamRepository teamRepository
  ) {
    this.idProvider = idProvider;
    this.scheduleDayRepository = scheduleDayRepository;
    this.teamRepository = teamRepository;
  }

  @Override
  public IdResponse handle(OrganizeMatchCommand input) throws RuntimeException {
    var firstTeam = teamRepository
        .findById(input.getFirstTeamId())
        .orElseThrow(() -> new NotFoundException("Team not found"));

    var secondTeam = teamRepository
        .findById(input.getSecondTeamId())
        .orElseThrow(() -> new NotFoundException("Team not found"));

    // Either find a schedule at that day or create a new one
    var scheduleDay = this.scheduleDayRepository
        .findByDate(input.getDate())
        .orElse(
            new ScheduleDay(idProvider.generate(), input.getDate())
        );

    scheduleDay.schedule(input.getMoment(), firstTeam, secondTeam);
    scheduleDayRepository.save(scheduleDay);

    return new IdResponse(scheduleDay.getId());
  }
}
