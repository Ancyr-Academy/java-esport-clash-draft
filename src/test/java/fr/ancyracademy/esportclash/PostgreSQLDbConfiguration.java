package fr.ancyracademy.esportclash;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
public class PostgreSQLDbConfiguration {

  @Bean
  @ServiceConnection
  PostgreSQLContainer postgreSqlContainer() {
    return new PostgreSQLContainer(DockerImageName.parse("postgres:latest"));
  }

  @Bean
  ObjectMapper objectMapper() {
    return new ObjectMapper();
  }
}
