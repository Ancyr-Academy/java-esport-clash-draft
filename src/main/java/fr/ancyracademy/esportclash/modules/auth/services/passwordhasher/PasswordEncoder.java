package fr.ancyracademy.esportclash.modules.auth.services.passwordhasher;

public interface PasswordEncoder {
  String hash(String password);

  boolean match(String clearPassword, String hashedPassword);
}
