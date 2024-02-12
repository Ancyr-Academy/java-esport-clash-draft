package fr.ancyracademy.esportclash.modules.schedule.commands;

import an.awesome.pipelinr.Command;
import fr.ancyracademy.esportclash.modules.schedule.ports.ScheduleDayRepository;
import fr.ancyracademy.esportclash.shared.exceptions.NotFoundException;

public class CancelMatchCommandHandler implements Command.Handler<CancelMatchCommand, Void> {

  private final ScheduleDayRepository scheduleDayRepository;

  public CancelMatchCommandHandler(ScheduleDayRepository scheduleDayRepository) {
    this.scheduleDayRepository = scheduleDayRepository;
  }

  @Override
  public Void handle(CancelMatchCommand input) throws RuntimeException {
    var scheduleDay = scheduleDayRepository.findByMatchId(input.getMatchId()).orElseThrow(
        () -> new NotFoundException("Match with id " + input.getMatchId() + " not found")
    );

    scheduleDay.cancel(input.getMatchId());

    if (scheduleDay.isEmpty()) {
      scheduleDayRepository.delete(scheduleDay);
    } else {
      scheduleDayRepository.save(scheduleDay);
    }

    return null;
  }
}
