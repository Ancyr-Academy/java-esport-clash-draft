package fr.ancyracademy.esportclash.modules.player.ports;

import fr.ancyracademy.esportclash.modules.player.model.Player;

import java.util.Optional;
import java.util.UUID;

public interface PlayerRepository {
  Optional<Player> findById(UUID id);

  void save(Player player);

  void delete(Player player);

  void clear();
}
