package fr.ancyracademy.javaesportclash.modules.player.model;

public enum Role {
  TOP,
  JUNGLE,
  MID,
  BOTTOM,
  SUPPORT;

  public static Role fromString(String role) {
    return switch (role) {
      case "TOP" -> Role.TOP;
      case "JUNGLE" -> Role.JUNGLE;
      case "MID" -> Role.MID;
      case "BOTTOM" -> Role.BOTTOM;
      case "SUPPORT" -> Role.SUPPORT;
      default -> throw new IllegalArgumentException("Invalid role: " + role);
    };
  }

  @Override
  public String toString() {
    return switch (this) {
      case TOP -> "TOP";
      case JUNGLE -> "JUNGLE";
      case MID -> "MID";
      case BOTTOM -> "BOTTOM";
      case SUPPORT -> "SUPPORT";
    };
  }
}


