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
    Player player = players.get(id);
    if (player == null) {
      return Optional.empty();
    }

    return Optional.of(new Player(player));
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
