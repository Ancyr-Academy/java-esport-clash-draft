package fr.ancyracademy.javaesportclash.modules.player.spring.controllers;

import fr.ancyracademy.javaesportclash.modules.player.model.Role;
import fr.ancyracademy.javaesportclash.modules.player.spring.dto.CreatePlayerDTO;
import fr.ancyracademy.javaesportclash.modules.player.usecases.CreatePlayerInput;
import fr.ancyracademy.javaesportclash.modules.player.usecases.CreatePlayerUseCase;
import fr.ancyracademy.javaesportclash.shared.IdResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/players")
public class PlayerController {
  private final CreatePlayerUseCase createPlayerUseCase;

  @Autowired
  public PlayerController(CreatePlayerUseCase createPlayerUseCase) {
    this.createPlayerUseCase = createPlayerUseCase;
  }

  @PostMapping
  public ResponseEntity<IdResponse> createPlayer(@Valid @RequestBody CreatePlayerDTO input) {
    System.out.println("Hello ! " + input.getName() + " " + input.getMainRole());

    var response = createPlayerUseCase.execute(new CreatePlayerInput(input.getName(), Role.fromString(input.getMainRole())));
    return new ResponseEntity<>(new IdResponse(response.getId()), HttpStatus.CREATED);
  }

  @GetMapping
  public void getPlayer() {
    return;
  }
}
