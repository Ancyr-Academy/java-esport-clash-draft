package fr.ancyracademy.esportclash.modules.auth.spring.controllers;

import an.awesome.pipelinr.Pipeline;
import fr.ancyracademy.esportclash.modules.auth.commands.LoginCommand;
import fr.ancyracademy.esportclash.modules.auth.commands.RegisterCommand;
import fr.ancyracademy.esportclash.modules.auth.viewmodel.LoggedInUserViewModel;
import fr.ancyracademy.esportclash.modules.auth.viewmodel.UserViewModel;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
  private final Pipeline pipeline;

  public AuthController(Pipeline pipeline) {
    this.pipeline = pipeline;
  }

  @PostMapping("/register")
  public ResponseEntity<UserViewModel> register(
      @Valid @RequestBody RegisterCommand command
  ) {
    var response = pipeline.send(command);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @PostMapping("/login")
  public ResponseEntity<LoggedInUserViewModel> login(
      @Valid @RequestBody LoginCommand command
  ) {
    var response = pipeline.send(command);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
