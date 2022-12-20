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
  @Column(name = "name")
  private String name;
  @NotNull
  @Column(name = "email", unique = true)
  private String email;
  @NotNull
  @Column(name = "number", unique = true)
  private String number;
  @NotNull
  @Column(name = "classification")
  private String classification;
  @NotNull
  @Column(name = "company")
  private String company;
  @NotNull
  @Column(name = "company_address")
  private String companyAddress;

  @JsonIgnore
  @OneToMany(fetch = FetchType.EAGER, mappedBy = "userId", cascade = CascadeType.ALL)
  private List<Project> myProject;

  @Builder
  public Member(String name, String email, String number, String classification, String company, String companyAddress) {
    this.name = name;
    this.email = email;
    this.number = number;
    this.classification = classification;
    this.company = company;
    this.companyAddress = companyAddress;
  }
}
