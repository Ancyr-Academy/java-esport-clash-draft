package fr.ancyracademy.esportclash.modules.auth.commands;

import an.awesome.pipelinr.Command;
import fr.ancyracademy.esportclash.modules.auth.viewmodel.LoggedInUserViewModel;

public class LoginCommand implements Command<LoggedInUserViewModel> {
  private final String emailAdress;

  private final String password;

  public LoginCommand(String emailAdress, String password) {
    this.emailAdress = emailAdress;
    this.password = password;
  }

  public String getEmailAdress() {
    return emailAdress;
  }

  public String getPassword() {
    return password;
  }
}
