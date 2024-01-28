package fr.ancyracademy.esportclash.modules.team.adapters.sql;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "teams")
public class SQLTeamEntity {
  @Id
  private String id;

  @Column
  private String name;

  @OneToMany(
      mappedBy = "team",
      cascade = CascadeType.ALL,
      orphanRemoval = true,
      fetch = FetchType.EAGER
  )
  private Set<SQLTeamMemberEntity> members;

  public SQLTeamEntity() {
  }

  public SQLTeamEntity(String id, String name) {
    this.id = id;
    this.name = name;
    this.members = new HashSet<>();
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

  public Set<SQLTeamMemberEntity> getMembers() {
    return members;
  }

  public void setMembers(Set<SQLTeamMemberEntity> members) {
    this.members = members;
  }

  public void addMember(SQLTeamMemberEntity member) {
    this.members.add(member);
  }
}
