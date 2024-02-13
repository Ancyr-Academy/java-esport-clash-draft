package fr.ancyracademy.esportclash.modules.auth.commands;

import an.awesome.pipelinr.Command;
import fr.ancyracademy.esportclash.modules.auth.viewmodel.UserViewModel;

public class RegisterCommand implements Command<UserViewModel> {
  private final String emailAdress;

  private final String password;

  public RegisterCommand(String emailAdress, String password) {
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
