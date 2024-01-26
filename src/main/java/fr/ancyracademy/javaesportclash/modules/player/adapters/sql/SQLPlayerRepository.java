package fr.ancyracademy.javaesportclash.modules.player.adapters.sql;

import fr.ancyracademy.javaesportclash.modules.player.model.Player;
import fr.ancyracademy.javaesportclash.modules.player.model.Role;
import fr.ancyracademy.javaesportclash.modules.player.ports.PlayerRepository;

import java.util.Optional;
import java.util.UUID;

public class SQLPlayerRepository implements PlayerRepository {
  static class Mapper {
    public Player toDomainModel(SQLPlayerEntity document) {
      return new Player(
          document.getId(),
          document.getName(),
          Role.valueOf(document.getMainRole())
      );
    }

    public SQLPlayerEntity toDocument(Player player) {
      SQLPlayerEntity document = new SQLPlayerEntity();
      document.setId(player.getId());
      document.setName(player.getName());
      document.setMainRole(player.getMainRole().name());

      return document;
    }

  }

  private final SQLPlayerDataAccessor dataAccessor;

  private final Mapper mapper = new Mapper();

  public SQLPlayerRepository(SQLPlayerDataAccessor dataAccessor) {
    this.dataAccessor = dataAccessor;
  }

  @Override
  public void save(Player player) {
    SQLPlayerEntity document = mapper.toDocument(player);
    dataAccessor.save(document);
  }

  @Override
  public Optional<Player> findById(UUID id) {
    Optional<SQLPlayerEntity> document = dataAccessor.findById(id);
    return document.map(mapper::toDomainModel);
  }

  @Override
  public void delete(Player player) {
    SQLPlayerEntity document = mapper.toDocument(player);
    dataAccessor.delete(document);
  }

  @Override
  public void clear() {
    dataAccessor.deleteAll();
  }
}
