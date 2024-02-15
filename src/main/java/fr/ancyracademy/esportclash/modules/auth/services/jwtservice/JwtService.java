package fr.ancyracademy.esportclash.modules.auth.services.jwtservice;

import fr.ancyracademy.esportclash.modules.auth.model.AuthUser;
import fr.ancyracademy.esportclash.modules.auth.model.User;

public interface JwtService {
  String tokenize(User user);

  AuthUser parse(String token);
}
