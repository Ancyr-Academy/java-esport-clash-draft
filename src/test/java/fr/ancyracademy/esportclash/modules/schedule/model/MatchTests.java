package fr.ancyracademy.esportclash.modules.schedule.model;

import fr.ancyracademy.esportclash.modules.player.model.Role;
import fr.ancyracademy.esportclash.modules.team.model.Team;

import java.util.ArrayList;

public class MatchTests {
  private Team createSKT() {
    var team = new Team(
        "skt",
        "SKT",
        new ArrayList<>());

    team.join("skt-top", Role.TOP);
    team.join("skt-jgl", Role.JUNGLE);
    team.join("skt-mid", Role.MID);
    team.join("skt-bot", Role.BOTTOM);
    team.join("skt-supp", Role.SUPPORT);

    return team;
  }

  private Team createFNC() {
    var team = new Team(
        "fnc",
        "Fnatic",
        new ArrayList<>());

    team.join("fnc-top", Role.TOP);
    team.join("fnc-jgl", Role.JUNGLE);
    team.join("fnc-mid", Role.MID);
    team.join("fnc-bot", Role.BOTTOM);
    team.join("fnc-supp", Role.SUPPORT);

    return team;
  }


}
