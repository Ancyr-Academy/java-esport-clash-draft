package fr.ancyracademy.esportclash.modules.auth.commands;

import an.awesome.pipelinr.Command;
import fr.ancyracademy.esportclash.modules.auth.viewmodel.UserViewModel;

public class RegisterCommand implements Command<UserViewModel> {
  private final String emailAddress;

  private final String password;

  public RegisterCommand(String emailAddress, String password) {
    this.emailAddress = emailAddress;
    this.password = password;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public String getPassword() {
    return password;
  }
}
