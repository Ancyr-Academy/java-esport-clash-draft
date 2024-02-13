package fr.ancyracademy.esportclash.modules.auth.spring.configuration;

import fr.ancyracademy.esportclash.modules.auth.services.jwtservice.ConcreteJwtService;
import fr.ancyracademy.esportclash.modules.auth.services.jwtservice.JwtService;
import fr.ancyracademy.esportclash.modules.auth.services.passwordhasher.BCryptEncoder;
import fr.ancyracademy.esportclash.modules.auth.services.passwordhasher.PasswordEncoder;
import fr.ancyracademy.esportclash.shared.date.DateProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthServicesConfiguration {
  @Bean
  PasswordEncoder getPasswordEncoder() {
    return new BCryptEncoder();
  }

  @Bean
  JwtService getJwtService(DateProvider dateProvider) {
    return new ConcreteJwtService(
        "super_secret_that_should_work_very_fine",
        3600,
        dateProvider

    );
  }
}
