package fr.ancyracademy.javaesportclash.modules.player.adapters.sql;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SQLPlayerDataAccessor extends JpaRepository<SQLPlayerEntity, UUID> {
}
