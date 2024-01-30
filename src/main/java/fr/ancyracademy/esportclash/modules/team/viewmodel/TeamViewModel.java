package fr.ancyracademy.esportclash.modules.team.viewmodel;

import java.util.List;

public record TeamViewModel(
    String id,
    String name,
    List<TeamMember> members) {
  public record TeamMember(
      String id,
      String name,
      String role
  ) {
  }
}
