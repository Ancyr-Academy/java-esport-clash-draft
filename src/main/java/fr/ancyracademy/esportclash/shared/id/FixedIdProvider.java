package fr.ancyracademy.esportclash.shared.id;

public class FixedIdProvider implements IdProvider {
  static final String id = "fixed-id";

  @Override
  public String generate() {
    return id;
  }
}
