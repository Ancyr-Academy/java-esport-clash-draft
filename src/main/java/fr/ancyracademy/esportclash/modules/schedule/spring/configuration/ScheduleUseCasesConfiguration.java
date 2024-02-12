package fr.ancyracademy.esportclash.modules.schedule.spring.configuration;

import fr.ancyracademy.esportclash.modules.schedule.commands.CancelMatchCommandHandler;
import fr.ancyracademy.esportclash.modules.schedule.commands.OrganizeMatchCommandHandler;
import fr.ancyracademy.esportclash.modules.schedule.ports.ScheduleDayRepository;
import fr.ancyracademy.esportclash.modules.team.ports.TeamRepository;
import fr.ancyracademy.esportclash.shared.id.IdProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ScheduleUseCasesConfiguration {
  @Bean
  public OrganizeMatchCommandHandler organizeMatchUseCase(
      IdProvider idProvider,
      ScheduleDayRepository scheduleDayRepository,
      TeamRepository teamRepository
  ) {
    return new OrganizeMatchCommandHandler(
        idProvider,
        scheduleDayRepository,
        teamRepository
    );
  }

  @Bean
  public CancelMatchCommandHandler cancelMatchUseCase(
      ScheduleDayRepository scheduleDayRepository
  ) {
    return new CancelMatchCommandHandler(
        scheduleDayRepository
    );
  }
}
