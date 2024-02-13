package fr.ancyracademy.esportclash.modules.auth.ports;

import fr.ancyracademy.esportclash.modules.auth.model.User;

import java.util.Optional;

public interface UserRepository {
  Optional<User> findById(String id);

  Optional<User> findByEmailAddress(String username);

  void save(User user);

  void delete(User user);

  void clear();
}
