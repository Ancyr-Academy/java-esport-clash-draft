package fr.ancyracademy.esportclash.modules.auth.commands;

import an.awesome.pipelinr.Command;
import fr.ancyracademy.esportclash.modules.auth.ports.UserRepository;
import fr.ancyracademy.esportclash.modules.auth.services.jwtservice.JwtService;
import fr.ancyracademy.esportclash.modules.auth.services.passwordhasher.PasswordEncoder;
import fr.ancyracademy.esportclash.modules.auth.viewmodel.LoggedInUserViewModel;
import fr.ancyracademy.esportclash.shared.exceptions.NotFoundException;

public class LoginCommandHandler implements Command.Handler<LoginCommand, LoggedInUserViewModel> {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;

  public LoginCommandHandler(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtService = jwtService;
  }

  @Override
  public LoggedInUserViewModel handle(LoginCommand command) {
    var user = userRepository
        .findByEmailAddress(command.getEmailAddress())
        .orElseThrow(
            () -> new NotFoundException("User not found")
        );

    var match = passwordEncoder.match(
        command.getPassword(),
        user.getPassword()
    );

    if (!match) {
      throw new NotFoundException("Password does not match");
    }

    var token = jwtService.tokenize(user);
    return new LoggedInUserViewModel(
        user.getId(),
        user.getEmailAddress(),
        token
    );
  }
}
