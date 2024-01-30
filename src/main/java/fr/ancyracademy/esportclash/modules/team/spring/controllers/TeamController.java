package fr.ancyracademy.esportclash.modules.team.spring.controllers;

import fr.ancyracademy.esportclash.modules.player.model.Role;
import fr.ancyracademy.esportclash.modules.team.spring.dto.AddPlayerToTeamDTO;
import fr.ancyracademy.esportclash.modules.team.spring.dto.CreateTeamDTO;
import fr.ancyracademy.esportclash.modules.team.usecases.*;
import fr.ancyracademy.esportclash.shared.IdResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teams")
public class TeamController {
  private final CreateTeamUseCase createTeamUseCase;

  private final DeleteTeamUseCase deleteTeamUseCase;

  private final AddPlayerToTeamUseCase addPlayerToTeamUseCase;

  private final RemovePlayerFromTeamUseCase removePlayerFromTeamUseCase;

  public TeamController(
      CreateTeamUseCase createTeamUseCase,
      DeleteTeamUseCase deleteTeamUseCase,
      AddPlayerToTeamUseCase addPlayerToTeamUseCase,
      RemovePlayerFromTeamUseCase removePlayerFromTeamUseCase
  ) {
    this.createTeamUseCase = createTeamUseCase;
    this.deleteTeamUseCase = deleteTeamUseCase;
    this.addPlayerToTeamUseCase = addPlayerToTeamUseCase;
    this.removePlayerFromTeamUseCase = removePlayerFromTeamUseCase;
  }

  @PostMapping
  public ResponseEntity<IdResponse> createTeam(
      @Valid @RequestBody CreateTeamDTO dto
  ) {
    var response = createTeamUseCase.execute(new CreateTeamInput(dto.getName()));
    return new ResponseEntity<>(new IdResponse(response.getId()), HttpStatus.CREATED);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> createTeam(
      @PathVariable("id") String id
  ) {
    deleteTeamUseCase.execute(new DeleteTeamInput(id));
    return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
  }

  @PostMapping("/{id}/players")
  public ResponseEntity<Void> addPlayerToTeam(
      @Valid @RequestBody AddPlayerToTeamDTO dto,
      @PathVariable("id") String teamId
  ) {
    addPlayerToTeamUseCase.execute(
        new AddPlayerToTeamInput(
            teamId,
            dto.getPlayerId(),
            Role.fromString(dto.getRole())
        )
    );

    return new ResponseEntity<>(null, HttpStatus.OK);
  }

  @DeleteMapping("/{teamId}/players/{playerId}")
  public ResponseEntity<Void> removePlayerFromTeam(
      @PathVariable("playerId") String playerId
  ) {
    removePlayerFromTeamUseCase.execute(
        new RemovePlayerFromTeamInput(
            playerId
        )
    );

    return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
  }
}
