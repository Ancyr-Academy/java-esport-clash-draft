package fr.ancyracademy.esportclash.modules.auth.spring.configuration;

import fr.ancyracademy.esportclash.modules.auth.adapters.sql.SQLUserDataAccessor;
import fr.ancyracademy.esportclash.modules.auth.adapters.sql.SQLUserRepository;
import fr.ancyracademy.esportclash.modules.auth.ports.UserRepository;
import fr.ancyracademy.esportclash.modules.team.adapters.sql.SQLTeamDataAccessor;
import fr.ancyracademy.esportclash.modules.team.adapters.sql.SQLTeamRepository;
import fr.ancyracademy.esportclash.modules.team.ports.TeamRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthAdapterConfiguration {
  @Bean
  UserRepository getUserRepository(SQLUserDataAccessor dataAccessor) {
    return new SQLUserRepository(dataAccessor);
  }
}
