package snust.kimth_lab.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
  @Column(name = "user_name")
  private String name;
  @Column(name = "user_email", unique = true)
  private String email;
  @Column(name = "user_class")
  private String userClassification;
  @Column(name = "company")
  private String company;
  @Column(name = "company_address")
  private String companyAddress;

  @JsonIgnore
  @OneToMany(fetch = FetchType.EAGER, mappedBy = "userId", cascade = CascadeType.ALL)
  private List<Project> myProject;

  @Builder
  public Member(String name, String email, String company, String companyAddress, String userClassification) {
    this.name = name;
    this.email = email;
    this.company = company;
    this.companyAddress = companyAddress;
    this.userClassification = userClassification;
  }
}
