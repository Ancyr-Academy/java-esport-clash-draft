package fr.ancyracademy.esportclash.modules.auth.model;

public class JwtUser {
  private final String id;
  private final String emailAdress;

  public JwtUser(String id, String emailAdress) {
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
