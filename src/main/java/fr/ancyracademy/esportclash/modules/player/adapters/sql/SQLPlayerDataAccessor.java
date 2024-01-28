package fr.ancyracademy.esportclash.modules.player.adapters.sql;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SQLPlayerDataAccessor extends JpaRepository<SQLPlayerEntity, String> {

}
