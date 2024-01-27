package fr.ancyracademy.esportclash.modules.player.spring.controllers;

import fr.ancyracademy.esportclash.modules.player.model.Role;
import fr.ancyracademy.esportclash.modules.player.spring.dto.ChangePlayerMainRoleDTO;
import fr.ancyracademy.esportclash.modules.player.spring.dto.CreatePlayerDTO;
import fr.ancyracademy.esportclash.modules.player.usecases.*;
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
  private final GetPlayerByIdUseCase getPlayerByIdUseCase;

  private final CreatePlayerUseCase createPlayerUseCase;
  private final ChangePlayerMainRoleUseCase changePlayerMainRoleUseCase;

  @Autowired
  public PlayerController(
      GetPlayerByIdUseCase getPlayerByIdUseCase,
      CreatePlayerUseCase createPlayerUseCase,
      ChangePlayerMainRoleUseCase changePlayerMainRoleUseCase
  ) {
    this.getPlayerByIdUseCase = getPlayerByIdUseCase;
    this.createPlayerUseCase = createPlayerUseCase;
    this.changePlayerMainRoleUseCase = changePlayerMainRoleUseCase;
  }


  @GetMapping("/{id}")
  public ResponseEntity<PlayerViewModel> getPlayer(
      @PathVariable("id") String id
  ) {
    var response = getPlayerByIdUseCase.execute(new GetPlayerByIdInput(id));
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<IdResponse> createPlayer(@Valid @RequestBody CreatePlayerDTO input) {
    var response = createPlayerUseCase.execute(new CreatePlayerInput(input.getName(), Role.fromString(input.getMainRole())));
    return new ResponseEntity<>(new IdResponse(response.getId()), HttpStatus.CREATED);
  }

  @Transactional()
  @PatchMapping("/{id}/main-role")
  public ResponseEntity<Void> changePlayerMainRole(@Valid @RequestBody ChangePlayerMainRoleDTO input, @PathVariable("id") String id) {


    changePlayerMainRoleUseCase.execute(new ChangePlayerMainRoleInput(id, Role.fromString(input.getMainRole())));
    return new ResponseEntity<>(null, HttpStatus.OK);
  }
}
