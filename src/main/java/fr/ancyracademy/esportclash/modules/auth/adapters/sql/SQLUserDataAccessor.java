package fr.ancyracademy.esportclash.modules.auth.adapters.sql;

import fr.ancyracademy.esportclash.modules.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SQLUserDataAccessor extends JpaRepository<User, String> {
  Optional<User> findByEmailAddress(String emailAddress);
}
