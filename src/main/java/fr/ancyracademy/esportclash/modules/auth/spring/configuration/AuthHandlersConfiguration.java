package fr.ancyracademy.esportclash.modules.auth.spring.configuration;

import fr.ancyracademy.esportclash.modules.auth.commands.LoginCommandHandler;
import fr.ancyracademy.esportclash.modules.auth.commands.RegisterCommandHandler;
import fr.ancyracademy.esportclash.modules.auth.ports.UserRepository;
import fr.ancyracademy.esportclash.modules.auth.services.jwtservice.JwtService;
import fr.ancyracademy.esportclash.modules.auth.services.passwordhasher.PasswordEncoder;
import fr.ancyracademy.esportclash.shared.id.IdProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthHandlersConfiguration {
  @Bean
  public RegisterCommandHandler createRegisterCommandHandler(
      IdProvider idProvider,
      UserRepository userRepository,
      PasswordEncoder passwordEncoder
  ) {
    return new RegisterCommandHandler(
        idProvider,
        userRepository,
        passwordEncoder
    );
  }

  @Bean
  public LoginCommandHandler createLoginCommandHandler(
      UserRepository userRepository,
      PasswordEncoder passwordEncoder,
      JwtService jwtService
  ) {
    return new LoginCommandHandler(
        userRepository,
        passwordEncoder,
        jwtService
    );
  }
}
