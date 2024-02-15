package fr.ancyracademy.esportclash.modules.auth.spring.configuration;

import fr.ancyracademy.esportclash.modules.auth.services.jwtservice.JwtService;
import fr.ancyracademy.esportclash.modules.auth.spring.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class AuthSecurityConfiguration {
  @Bean
  SecurityFilterChain defaultSecurityFilterChain(
      HttpSecurity http,
      JwtService jwtService
  ) throws Exception {
    http
        .addFilterBefore(
            new JwtAuthenticationFilter(jwtService),
            UsernamePasswordAuthenticationFilter.class
        )
        .authorizeHttpRequests(authorizeRequests ->
            authorizeRequests
                .anyRequest().permitAll()
        )
        .formLogin(AbstractHttpConfigurer::disable)
        .httpBasic(AbstractHttpConfigurer::disable)
        .csrf(AbstractHttpConfigurer::disable)
        .sessionManagement(it ->
            it.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

    return http.build();
  }
}
