package fr.ancyracademy.esportclash.modules.team.spring.controllers;

import an.awesome.pipelinr.Pipeline;
import fr.ancyracademy.esportclash.modules.player.model.Role;
import fr.ancyracademy.esportclash.modules.team.commands.AddPlayerToTeamCommand;
import fr.ancyracademy.esportclash.modules.team.commands.CreateTeamCommand;
import fr.ancyracademy.esportclash.modules.team.commands.DeleteTeamCommand;
import fr.ancyracademy.esportclash.modules.team.commands.RemovePlayerFromTeamCommand;
import fr.ancyracademy.esportclash.modules.team.queries.GetTeamByIdQuery;
import fr.ancyracademy.esportclash.modules.team.spring.dto.AddPlayerToTeamDTO;
import fr.ancyracademy.esportclash.modules.team.spring.dto.CreateTeamDTO;
import fr.ancyracademy.esportclash.modules.team.viewmodel.TeamViewModel;
import fr.ancyracademy.esportclash.shared.IdResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teams")
public class TeamController {
  private final Pipeline pipeline;

  public TeamController(Pipeline pipeline) {
    this.pipeline = pipeline;
  }

  @PostMapping
  public ResponseEntity<IdResponse> createTeam(
      @Valid @RequestBody CreateTeamDTO dto
  ) {
    var response = pipeline.send(new CreateTeamCommand(dto.getName()));
    return new ResponseEntity<>(new IdResponse(response.getId()), HttpStatus.CREATED);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> createTeam(
      @PathVariable("id") String id
  ) {
    pipeline.send(new DeleteTeamCommand(id));
    return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
  }

  @PostMapping("/{id}/players")
  public ResponseEntity<Void> addPlayerToTeam(
      @Valid @RequestBody AddPlayerToTeamDTO dto,
      @PathVariable("id") String teamId
  ) {
    pipeline.send(
        new AddPlayerToTeamCommand(
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
    pipeline.send(
        new RemovePlayerFromTeamCommand(
            playerId
        )
    );

    return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
  }

  @GetMapping("/{id}")
  public ResponseEntity<TeamViewModel> getTeamById(
      @PathVariable("id") String id
  ) {
    var team = pipeline.send(new GetTeamByIdQuery(id));
    return new ResponseEntity<>(team, HttpStatus.OK);
  }
}
