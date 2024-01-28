package fr.ancyracademy.esportclash.modules.team.spring.configuration;

import fr.ancyracademy.esportclash.modules.team.adapters.sql.SQLTeamDataAccessor;
import fr.ancyracademy.esportclash.modules.team.adapters.sql.SQLTeamRepository;
import fr.ancyracademy.esportclash.modules.team.ports.TeamRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PlayerAdapterConfiguration {
  @Bean
  TeamRepository getTeamRepository(SQLTeamDataAccessor dataAccessor) {
    return new SQLTeamRepository(dataAccessor);
  }
}
