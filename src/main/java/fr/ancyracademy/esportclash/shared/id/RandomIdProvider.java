package fr.ancyracademy.esportclash.shared.id;

import java.util.UUID;

public class RandomIdProvider implements IdProvider {
  @Override
  public String generate() {
    return UUID.randomUUID().toString();
  }
}
