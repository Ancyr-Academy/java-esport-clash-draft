package fr.ancyracademy.esportclash.modules.team.adapters.sql;

import fr.ancyracademy.esportclash.PostgreSQLDbConfiguration;
import fr.ancyracademy.esportclash.modules.player.model.Player;
import fr.ancyracademy.esportclash.modules.player.model.Role;
import fr.ancyracademy.esportclash.modules.player.ports.PlayerRepository;
import fr.ancyracademy.esportclash.modules.team.model.Team;
import fr.ancyracademy.esportclash.modules.team.ports.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Import(PostgreSQLDbConfiguration.class)
public class SQLTeamRepositoryTests {
  private final Player faker = new Player("faker", "Faker", Role.MID);
  private final Player gumayusi = new Player("gumayusi", "Gumayusi", Role.BOTTOM);

  @Autowired
  private TeamRepository repository;
  @Autowired
  private PlayerRepository playerRepository;

  @BeforeEach
  void setUp() {
    repository.clear();
    playerRepository.clear();

    playerRepository.save(faker);
    playerRepository.save(gumayusi);
  }

  @Test
  void shouldSaveTeam() {
    var originalTeam = new Team("skt", "SKT", new ArrayList<>());
    originalTeam.join(faker.getId(), Role.MID);

    repository.save(originalTeam);

    var savedTeamQuery = repository.findById(originalTeam.getId());
    assertTrue(savedTeamQuery.isPresent());

    var savedTeam = savedTeamQuery.get();

    assertEquals(originalTeam.getId(), savedTeam.getId());
    assertEquals(originalTeam.getName(), savedTeam.getName());

    assertEquals(1, savedTeam.getMembers().size());
    assertTrue(savedTeam.hasPlayer(faker.getId()));
  }

  @Test
  void shouldAddMemberToTeam() {
    var originalTeam = new Team("skt", "SKT", new ArrayList<>());
    originalTeam.join(faker.getId(), Role.MID);
    repository.save(originalTeam);

    originalTeam.join(gumayusi.getId(), Role.BOTTOM);
    repository.save(originalTeam);

    var savedTeam = repository.findById(originalTeam.getId()).get();

    assertEquals(2, savedTeam.getMembers().size());
    assertTrue(savedTeam.hasPlayer(faker.getId()));
    assertTrue(savedTeam.hasPlayer(gumayusi.getId()));
  }

  @Test
  void shouldRemoveMemberFromTheTeam() {
    var originalTeam = new Team("skt", "SKT", new ArrayList<>());
    originalTeam.join(faker.getId(), Role.MID);
    repository.save(originalTeam);

    originalTeam.leave(faker.getId());
    repository.save(originalTeam);

    var savedTeam = repository.findById(originalTeam.getId()).get();

    assertEquals(0, savedTeam.getMembers().size());
  }

  @Test
  void shouldFindATeamByPlayerId() {
    var originalTeam = new Team("skt", "SKT", new ArrayList<>());
    originalTeam.join(faker.getId(), Role.MID);
    repository.save(originalTeam);

    var savedTeamQuery = repository.findByPlayerId(faker.getId());
    assertTrue(savedTeamQuery.isPresent());

    var savedTeam = savedTeamQuery.get();

    assertEquals(originalTeam.getId(), savedTeam.getId());
  }

  @Test
  void shouldDeleteATeam() {
    var originalTeam = new Team("skt", "SKT", new ArrayList<>());
    originalTeam.join(faker.getId(), Role.MID);
    repository.save(originalTeam);

    repository.delete(originalTeam);

    var savedTeamQuery = repository.findById(originalTeam.getId());
    assertTrue(savedTeamQuery.isEmpty());
  }
}
