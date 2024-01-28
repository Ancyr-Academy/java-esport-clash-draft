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
    SQLPlayerEntity document = new SQLPlayerEntity();
    document.setId(player.getId());
    document.setName(player.getName());
    document.setMainRole(player.getMainRole());

    dataAccessor.save(document);
  }

  @Override
  public Optional<Player> findById(String id) {
    Optional<SQLPlayerEntity> document = dataAccessor.findById(id);
    return document.map(this::fromSql);
  }

  @Override
  public void delete(Player player) {
    dataAccessor.deleteById(player.getId());
  }

  @Override
  public void clear() {
    dataAccessor.deleteAll();
  }

  private Player fromSql(SQLPlayerEntity document) {
    return new Player(
        document.getId(),
        document.getName(),
        document.getMainRole()
    );
  }
}
