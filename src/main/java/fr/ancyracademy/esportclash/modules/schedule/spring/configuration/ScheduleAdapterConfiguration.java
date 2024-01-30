package fr.ancyracademy.esportclash.modules.schedule.spring.configuration;

import fr.ancyracademy.esportclash.modules.schedule.adapters.sql.SQLScheduleDayAccessor;
import fr.ancyracademy.esportclash.modules.schedule.adapters.sql.SQLScheduleDayRepository;
import fr.ancyracademy.esportclash.modules.schedule.ports.ScheduleDayRepository;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ScheduleAdapterConfiguration {
  @Bean
  public ScheduleDayRepository scheduleDayRepository(SQLScheduleDayAccessor accessor, EntityManager entityManager) {
    return new SQLScheduleDayRepository(accessor, entityManager);
  }
}
