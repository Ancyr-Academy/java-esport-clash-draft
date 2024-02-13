package fr.ancyracademy.esportclash.modules.auth.services.passwordhasher;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptEncoder implements PasswordEncoder {
  private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

  public String hash(String password) {
    return encoder.encode(password);
  }

  public boolean match(String clearPassword, String hashedPassword) {
    return encoder.matches(clearPassword, hashedPassword);
  }
}
