package fr.ancyracademy.javaesportclash.modules.player.adapters.ram;

import fr.ancyracademy.javaesportclash.modules.player.model.Player;
import fr.ancyracademy.javaesportclash.modules.player.ports.PlayerRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class InMemoryPlayerRepository implements PlayerRepository {
  private final Map<UUID, Player> players = new HashMap<>();

  @Override
  public Optional<Player> findById(UUID id) {
    return players.values().stream()
        .filter(player -> player.getId().equals(id))
        .findFirst()
        .map(Player::new);
  }

  @Override
  public void save(Player player) {
    players.put(player.getId(), player);
  }

  @Override
  public void delete(Player player) {
    players.remove(player.getId());
  }

  @Override
  public void clear() {
    players.clear();
  }
}
