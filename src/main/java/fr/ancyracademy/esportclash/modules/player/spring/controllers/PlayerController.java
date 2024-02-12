package fr.ancyracademy.esportclash.modules.player.spring.controllers;

import an.awesome.pipelinr.Pipeline;
import fr.ancyracademy.esportclash.modules.player.commands.ChangePlayerMainRoleCommand;
import fr.ancyracademy.esportclash.modules.player.commands.CreatePlayerCommand;
import fr.ancyracademy.esportclash.modules.player.model.Role;
import fr.ancyracademy.esportclash.modules.player.queries.GetPlayerByIdQuery;
import fr.ancyracademy.esportclash.modules.player.spring.dto.ChangePlayerMainRoleDTO;
import fr.ancyracademy.esportclash.modules.player.spring.dto.CreatePlayerDTO;
import fr.ancyracademy.esportclash.modules.player.viewmodel.PlayerViewModel;
import fr.ancyracademy.esportclash.shared.IdResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/players")
public class PlayerController {
  private final Pipeline pipeline;

  @Autowired
  public PlayerController(
      Pipeline pipeline
  ) {
    this.pipeline = pipeline;
  }


  @GetMapping("/{id}")
  public ResponseEntity<PlayerViewModel> getPlayer(
      @PathVariable("id") String id
  ) {
    var response = this.pipeline.send(new GetPlayerByIdQuery(id));
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<IdResponse> createPlayer(@Valid @RequestBody CreatePlayerDTO input) {
    var response = this.pipeline.send(new CreatePlayerCommand(input.getName(), Role.fromString(input.getMainRole())));
    return new ResponseEntity<>(new IdResponse(response.getId()), HttpStatus.CREATED);
  }

  @Transactional()
  @PatchMapping("/{id}/main-role")
  public ResponseEntity<Void> changePlayerMainRole(@Valid @RequestBody ChangePlayerMainRoleDTO input, @PathVariable("id") String id) {
    this.pipeline.send(new ChangePlayerMainRoleCommand(id, Role.fromString(input.getMainRole())));
    return new ResponseEntity<>(null, HttpStatus.OK);
  }
}
