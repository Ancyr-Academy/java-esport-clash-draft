package fr.ancyracademy.esportclash.modules.schedule.usecases;

import fr.ancyracademy.esportclash.shared.UseCase;

public class CancelMatchUseCase implements UseCase<CancelMatchInput, Void> {
  @Override
  public Void execute(CancelMatchInput cancelMatchInput) throws RuntimeException {
    return null;
  }
}
