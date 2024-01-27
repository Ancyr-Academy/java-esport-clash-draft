package fr.ancyracademy.javaesportclash.spring.configurations;

import fr.ancyracademy.javaesportclash.shared.id.IdProvider;
import fr.ancyracademy.javaesportclash.shared.id.RandomIdProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
  @Bean
  public IdProvider idProvider() {
    return new RandomIdProvider();
  }
}
