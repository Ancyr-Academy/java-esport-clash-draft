package fr.ancyracademy.esportclash.modules.auth.adapters.sql;

import fr.ancyracademy.esportclash.modules.auth.model.User;
import fr.ancyracademy.esportclash.modules.auth.ports.UserRepository;

import java.util.Optional;

public class SQLUserRepository implements UserRepository {
  private final SQLUserDataAccessor dataAccessor;

  public SQLUserRepository(SQLUserDataAccessor dataAccessor) {
    this.dataAccessor = dataAccessor;
  }

  @Override
  public Optional<User> findById(String id) {
    return dataAccessor.findById(id);
  }

  @Override
  public Optional<User> findByEmailAddress(String username) {
    return dataAccessor.findByEmailAddress(username);
  }

  @Override
  public void save(User user) {
    dataAccessor.save(user);
  }

  @Override
  public void delete(User user) {
    dataAccessor.delete(user);
  }

  @Override
  public void clear() {
    dataAccessor.deleteAll();
  }
}
