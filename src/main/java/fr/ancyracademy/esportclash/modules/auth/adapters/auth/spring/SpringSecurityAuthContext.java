package fr.ancyracademy.esportclash.modules.auth.adapters.auth.spring;

import fr.ancyracademy.esportclash.modules.auth.model.AuthUser;
import fr.ancyracademy.esportclash.modules.auth.ports.AuthContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class SpringSecurityAuthContext implements AuthContext {
  @Override
  public boolean isAuthenticated() {
    return SecurityContextHolder
        .getContext()
        .getAuthentication()
        .isAuthenticated();
  }

  @Override
  public Optional<AuthUser> getUser() {
    return Optional.ofNullable(
            SecurityContextHolder
                .getContext()
                .getAuthentication()
        )
        .map(auth -> {
          if (auth.getPrincipal() instanceof AuthUser) {
            return (AuthUser) auth.getPrincipal();
          }

          return null;
        });
  }
}
