package fr.ancyracademy.esportclash.modules.auth.commands;

import an.awesome.pipelinr.Command;
import fr.ancyracademy.esportclash.modules.auth.model.User;
import fr.ancyracademy.esportclash.modules.auth.ports.UserRepository;
import fr.ancyracademy.esportclash.modules.auth.services.passwordhasher.PasswordEncoder;
import fr.ancyracademy.esportclash.modules.auth.viewmodel.UserViewModel;
import fr.ancyracademy.esportclash.shared.id.IdProvider;

public class RegisterCommandHandler implements Command.Handler<RegisterCommand, UserViewModel> {
  private final IdProvider idProvider;
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public RegisterCommandHandler(IdProvider idProvider, UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.idProvider = idProvider;
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public UserViewModel handle(RegisterCommand command) {
    var encoded = passwordEncoder.hash(command.getPassword());
    var user = new User(
        idProvider.generate(),
        command.getEmailAddress(),
        encoded);

    userRepository.save(user);

    return new UserViewModel(user.getId(), user.getEmailAddress());
  }
}
