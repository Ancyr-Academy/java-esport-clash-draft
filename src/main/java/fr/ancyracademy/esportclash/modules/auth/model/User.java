package fr.ancyracademy.esportclash.modules.auth.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
  @Id
  private String id;

  @Column
  private String emailAddress;

  @Column
  private String password;

  public User() {
  }

  public User(String id, String emailAddress, String password) {
    this.id = id;
    this.emailAddress = emailAddress;
    this.password = password;
  }

  public String getId() {
    return id;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public String getPassword() {
    return password;
  }
}
