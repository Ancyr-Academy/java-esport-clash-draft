package fr.ancyracademy.esportclash.modules.team.usecases;

import fr.ancyracademy.esportclash.modules.team.adapters.ram.InMemoryTeamRepository;
import fr.ancyracademy.esportclash.shared.id.FixedIdProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateTeamUseCaseTests {
  InMemoryTeamRepository teamRepository = new InMemoryTeamRepository();

  FixedIdProvider idProvider = new FixedIdProvider();

  @BeforeEach
  void setUp() {
    teamRepository.clear();
  }

  CreateTeamUseCase createUseCase() {
    return new CreateTeamUseCase(teamRepository, idProvider);
  }

  @Nested
  class Scenario_HappyPath {
    CreateTeamInput input = new CreateTeamInput("SKT");

    @Test
    void shouldReturnId() {
      var useCase = createUseCase();
      var response = useCase.execute(input);

      assertEquals(idProvider.generate(), response.getId());
    }

    @Test
    void shouldCreateTeam() {
      var useCase = createUseCase();
      useCase.execute(input);

      var team = teamRepository.findById(idProvider.generate()).get();
      assertEquals("SKT", team.getName());
    }
  }
}
