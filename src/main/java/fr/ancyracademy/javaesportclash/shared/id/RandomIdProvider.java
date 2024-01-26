package fr.ancyracademy.javaesportclash.shared.id;

import java.util.UUID;

public class RandomIdProvider implements IdProvider {
  @Override
  public UUID generate() {
    return UUID.randomUUID();
  }
}
