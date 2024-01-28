package fr.ancyracademy.esportclash.modules.schedule.usecases;

import fr.ancyracademy.esportclash.modules.schedule.ports.ScheduleDayRepository;
import fr.ancyracademy.esportclash.shared.UseCase;
import fr.ancyracademy.esportclash.shared.exceptions.NotFoundException;

public class CancelMatchUseCase implements UseCase<CancelMatchInput, Void> {

  private final ScheduleDayRepository scheduleDayRepository;

  public CancelMatchUseCase(ScheduleDayRepository scheduleDayRepository) {
    this.scheduleDayRepository = scheduleDayRepository;
  }

  @Override
  public Void execute(CancelMatchInput input) throws RuntimeException {
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
