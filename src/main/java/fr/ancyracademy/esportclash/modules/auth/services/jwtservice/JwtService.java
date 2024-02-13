package fr.ancyracademy.esportclash.modules.auth.services.jwtservice;

import fr.ancyracademy.esportclash.modules.auth.model.JwtUser;
import fr.ancyracademy.esportclash.modules.auth.model.User;

public interface JwtService {
  String tokenize(User user);

  JwtUser parse(String token);
}
