package fr.ancyracademy.esportclash.modules.team.usecases;

import fr.ancyracademy.esportclash.modules.team.adapters.ram.InMemoryTeamRepository;
import fr.ancyracademy.esportclash.modules.team.model.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeleteTeamUseCaseTests {
  InMemoryTeamRepository teamRepository = new InMemoryTeamRepository();

  Team team = new Team("skt", "SKT");

  @BeforeEach
  void setUp() {
    teamRepository.clear();
    teamRepository.save(team);
  }

  DeleteTeamUseCase createUseCase() {
    return new DeleteTeamUseCase(teamRepository);
  }

  @Nested
  class Scenario_HappyPath {
    DeleteTeamInput input = new DeleteTeamInput(team.getId());

    @Test
    void shouldDeleteTeam() {
      var useCase = createUseCase();
      useCase.execute(input);

      var team = teamRepository.findById(input.id());
      assertTrue(team.isEmpty());
    }
  }
}
