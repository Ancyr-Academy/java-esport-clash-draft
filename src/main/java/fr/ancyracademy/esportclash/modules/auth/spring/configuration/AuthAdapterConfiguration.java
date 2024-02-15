package fr.ancyracademy.esportclash.modules.auth.spring.configuration;

import fr.ancyracademy.esportclash.modules.auth.adapters.auth.spring.SpringSecurityAuthContext;
import fr.ancyracademy.esportclash.modules.auth.adapters.persistence.sql.SQLUserDataAccessor;
import fr.ancyracademy.esportclash.modules.auth.adapters.persistence.sql.SQLUserRepository;
import fr.ancyracademy.esportclash.modules.auth.ports.AuthContext;
import fr.ancyracademy.esportclash.modules.auth.ports.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthAdapterConfiguration {
  @Bean
  UserRepository getUserRepository(SQLUserDataAccessor dataAccessor) {
    return new SQLUserRepository(dataAccessor);
  }

  @Bean
  AuthContext getAuthContext() {
    return new SpringSecurityAuthContext();
  }
}
