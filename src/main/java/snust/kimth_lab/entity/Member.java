package snust.kimth_lab.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "member")
public class Member {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotNull
  @Column(name = "email")
  private String email;
  @NotNull
  @Column(name = "password")
  private String password;
  @NotNull
  @Column(name = "name")
  private String name;
  @NotNull
  @Column(name = "number")
  private String number;
  @NotNull
  @Column(name = "classification")
  private String classification;
  @NotNull
  @Column(name = "company")
  private String company;
//  @NotNull
//  @Column(name = "company_address")
//  private String companyAddress;

  //   i want to select one of fk(user_id) but can't do this becuz the fk is composite.
  @JsonIgnore
  @OneToMany(fetch = FetchType.EAGER, mappedBy = "member")
  private List<Project> myProject;

  @Builder
  public Member(String email, String password, String name, String number, String classification, String company) {
    this.email = email;
    this.password = password;
    this.name = name;
    this.number = number;
    this.classification = classification;
    this.company = company;
  }
}
