package fr.ancyracademy.esportclash.spring.configurations;

import fr.ancyracademy.esportclash.shared.id.IdProvider;
import fr.ancyracademy.esportclash.shared.id.RandomIdProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
  @Bean
  public IdProvider idProvider() {
    return new RandomIdProvider();
  }
}
