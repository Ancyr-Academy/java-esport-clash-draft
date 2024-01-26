package fr.ancyracademy.javaesportclash.shared.id;

import java.util.UUID;

public class FixedIdProvider implements IdProvider {
  static final UUID id = UUID.fromString("00000000-0000-0000-0000-000000000000");

  @Override
  public UUID generate() {
    return id;
  }
}
