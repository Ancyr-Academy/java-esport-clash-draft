package fr.ancyracademy.esportclash.modules.team.commands;

import fr.ancyracademy.esportclash.modules.team.adapters.ram.InMemoryTeamRepository;
import fr.ancyracademy.esportclash.modules.team.model.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeleteTeamCommandHandlerTests {
  InMemoryTeamRepository teamRepository = new InMemoryTeamRepository();

  Team team = new Team("skt", "SKT");

  @BeforeEach
  void setUp() {
    teamRepository.clear();
    teamRepository.save(team);
  }

  DeleteTeamCommandHandler createUseCase() {
    return new DeleteTeamCommandHandler(teamRepository);
  }

  @Nested
  class Scenario_HappyPath {
    DeleteTeamCommand input = new DeleteTeamCommand(team.getId());

    @Test
    void shouldDeleteTeam() {
      var useCase = createUseCase();
      useCase.handle(input);

      var team = teamRepository.findById(input.getId());
      assertTrue(team.isEmpty());
    }
  }
}
