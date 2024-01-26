package fr.ancyracademy.javaesportclash.modules.player.adapters.mongo;

import fr.ancyracademy.javaesportclash.modules.player.model.Player;
import fr.ancyracademy.javaesportclash.modules.player.model.Role;
import fr.ancyracademy.javaesportclash.modules.player.ports.PlayerRepository;

import java.util.Optional;
import java.util.UUID;

public class MongoPlayerRepository implements PlayerRepository {
  static class Mapper {
    public Player toDomainModel(MongoPlayerDocument document) {
      return new Player(
          document.getId(),
          document.getName(),
          Role.valueOf(document.getMainRole())
      );
    }

    public MongoPlayerDocument toDocument(Player player) {
      MongoPlayerDocument document = new MongoPlayerDocument();
      document.setId(player.getId());
      document.setName(player.getName());
      document.setMainRole(player.getMainRole().name());

      return document;
    }

  }

  private final MongoPlayerDataAccessor dataAccessor;

  private final Mapper mapper = new Mapper();

  public MongoPlayerRepository(MongoPlayerDataAccessor dataAccessor) {
    this.dataAccessor = dataAccessor;
  }

  @Override
  public void save(Player player) {
    MongoPlayerDocument document = mapper.toDocument(player);
    dataAccessor.save(document);
  }

  @Override
  public Optional<Player> findById(UUID id) {
    Optional<MongoPlayerDocument> document = dataAccessor.findById(id);
    return document.map(mapper::toDomainModel);
  }

  @Override
  public void delete(Player player) {
    MongoPlayerDocument document = mapper.toDocument(player);
    dataAccessor.delete(document);
  }

  @Override
  public void clear() {
    dataAccessor.deleteAll();
  }
}
