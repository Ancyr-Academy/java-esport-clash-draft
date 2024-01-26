package fr.ancyracademy.javaesportclash.modules.player.adapters.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface MongoPlayerDataAccessor extends MongoRepository<MongoPlayerDocument, UUID> {
}
