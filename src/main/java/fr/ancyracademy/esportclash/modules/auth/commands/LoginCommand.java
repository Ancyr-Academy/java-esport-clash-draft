package fr.ancyracademy.esportclash.modules.auth.commands;

import an.awesome.pipelinr.Command;
import fr.ancyracademy.esportclash.modules.auth.viewmodel.LoggedInUserViewModel;

public class LoginCommand implements Command<LoggedInUserViewModel> {
  private final String emailAddress;

  private final String password;

  public LoginCommand(String emailAddress, String password) {
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
