package fr.ancyracademy.esportclash.modules.auth.adapters.persistence.ram;

import fr.ancyracademy.esportclash.modules.auth.model.User;
import fr.ancyracademy.esportclash.modules.auth.ports.UserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryUserRepository implements UserRepository {
  private final Map<String, User> users = new HashMap<>();

  @Override
  public Optional<User> findById(String id) {
    return Optional.ofNullable(users.get(id));
  }

  @Override
  public Optional<User> findByEmailAddress(String username) {
    return users.values().stream()
        .filter(user -> user.getEmailAddress().equals(username))
        .findFirst();
  }

  @Override
  public void save(User user) {
    users.put(user.getId(), user);
  }

  @Override
  public void delete(User user) {
    users.remove(user.getId());
  }

  @Override
  public void clear() {
    users.clear();
  }
}
