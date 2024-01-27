package fr.ancyracademy.esportclash.modules.player.ports;

import fr.ancyracademy.esportclash.modules.player.model.Player;

import java.util.Optional;

public interface PlayerRepository {
  Optional<Player> findById(String id);

  void save(Player player);

  void delete(Player player);

  void clear();
}
