package fr.ancyracademy.esportclash.modules.schedule.usecases;

public class CancelMatchInput {
  private String matchId;

  public CancelMatchInput(String matchId) {
    this.matchId = matchId;
  }

  public String getMatchId() {
    return matchId;
  }

  public void setMatchId(String matchId) {
    this.matchId = matchId;
  }
}
