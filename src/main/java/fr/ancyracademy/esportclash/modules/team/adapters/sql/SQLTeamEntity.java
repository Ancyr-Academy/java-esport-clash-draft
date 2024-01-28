package fr.ancyracademy.esportclash.modules.team.adapters.sql;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "teams")
public class SQLTeamEntity {
  @Id
  private String id;

  @Column
  private String name;

  @OneToMany(
      mappedBy = "id.teamId",
      cascade = CascadeType.ALL,
      orphanRemoval = true,
      fetch = FetchType.EAGER
  )
  private List<SQLTeamMemberEntity> members;

  public SQLTeamEntity() {
  }

  public SQLTeamEntity(String id, String name, List<SQLTeamMemberEntity> members) {
    this.id = id;
    this.name = name;
    this.members = members;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<SQLTeamMemberEntity> getMembers() {
    return members;
  }

  public void setMembers(List<SQLTeamMemberEntity> members) {
    this.members = members;
  }

  public void addMember(SQLTeamMemberEntity member) {
    this.members.add(member);
  }
}
