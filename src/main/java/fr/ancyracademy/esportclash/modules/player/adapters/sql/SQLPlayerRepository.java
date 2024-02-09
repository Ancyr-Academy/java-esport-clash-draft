package fr.ancyracademy.esportclash.modules.player.adapters.sql;

import fr.ancyracademy.esportclash.modules.player.model.Player;
import fr.ancyracademy.esportclash.modules.player.ports.PlayerRepository;

import java.util.Optional;

public class SQLPlayerRepository implements PlayerRepository {
  private final SQLPlayerDataAccessor dataAccessor;

  public SQLPlayerRepository(SQLPlayerDataAccessor dataAccessor) {
    this.dataAccessor = dataAccessor;
  }

  @Override
  public void save(Player player) {
    dataAccessor.save(player);
  }

  @Override
  public Optional<Player> findById(String id) {
    return dataAccessor.findById(id);
  }

  @Override
  public void delete(Player player) {
    dataAccessor.deleteById(player.getId());
  }

  @Override
  public void clear() {
    dataAccessor.deleteAll();
  }
}
