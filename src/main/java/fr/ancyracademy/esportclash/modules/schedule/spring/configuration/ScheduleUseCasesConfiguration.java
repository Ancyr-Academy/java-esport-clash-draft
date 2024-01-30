package fr.ancyracademy.esportclash.modules.schedule.spring.configuration;

import fr.ancyracademy.esportclash.modules.schedule.ports.ScheduleDayRepository;
import fr.ancyracademy.esportclash.modules.schedule.usecases.CancelMatchUseCase;
import fr.ancyracademy.esportclash.modules.schedule.usecases.OrganizeMatchUseCase;
import fr.ancyracademy.esportclash.modules.team.ports.TeamRepository;
import fr.ancyracademy.esportclash.shared.id.IdProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ScheduleUseCasesConfiguration {
  @Bean
  public OrganizeMatchUseCase organizeMatchUseCase(
      IdProvider idProvider,
      ScheduleDayRepository scheduleDayRepository,
      TeamRepository teamRepository
  ) {
    return new OrganizeMatchUseCase(
        idProvider,
        scheduleDayRepository,
        teamRepository
    );
  }

  @Bean
  public CancelMatchUseCase cancelMatchUseCase(
      ScheduleDayRepository scheduleDayRepository
  ) {
    return new CancelMatchUseCase(
        scheduleDayRepository
    );
  }
}
