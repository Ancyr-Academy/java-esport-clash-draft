package fr.ancyracademy.javaesportclash.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "fr.ancyracademy.javaesportclash")
@EnableMongoRepositories(basePackages = "fr.ancyracademy.javaesportclash")
public class JavaEsportClashApplication {

  public static void main(String[] args) {
    SpringApplication.run(JavaEsportClashApplication.class, args);
  }

}
