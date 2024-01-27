package fr.ancyracademy.esportclash.modules.player.adapters.ram;

import fr.ancyracademy.esportclash.modules.player.model.Player;
import fr.ancyracademy.esportclash.modules.player.ports.PlayerRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryPlayerRepository implements PlayerRepository {
  private final Map<String, Player> players = new HashMap<>();

  @Override
  public Optional<Player> findById(String id) {
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
