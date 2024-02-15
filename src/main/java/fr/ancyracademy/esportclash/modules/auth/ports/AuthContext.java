package fr.ancyracademy.esportclash.modules.auth.ports;

import fr.ancyracademy.esportclash.modules.auth.model.AuthUser;

import java.util.Optional;

public interface AuthContext {
  boolean isAuthenticated();

  Optional<AuthUser> getUser();
}
