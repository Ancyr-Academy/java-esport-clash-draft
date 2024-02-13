package fr.ancyracademy.esportclash.modules.auth.viewmodel;

public class LoggedInUserViewModel {
  public final String id;

  public final String emailAddress;

  private final String jwt;

  public LoggedInUserViewModel(
      String id,
      String emailAddress,
      String jwt) {
    this.id = id;
    this.emailAddress = emailAddress;
    this.jwt = jwt;
  }

  public String getId() {
    return id;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public String getJwt() {
    return jwt;
  }
}
