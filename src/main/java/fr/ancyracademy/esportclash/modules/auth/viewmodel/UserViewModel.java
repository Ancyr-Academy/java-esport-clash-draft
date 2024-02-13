package fr.ancyracademy.esportclash.modules.auth.viewmodel;

public class UserViewModel {
  public final String id;

  public final String emailAdress;

  public UserViewModel(String id, String emailAdress) {
    this.id = id;
    this.emailAdress = emailAdress;
  }

  public String getId() {
    return id;
  }

  public String getEmailAdress() {
    return emailAdress;
  }
}
