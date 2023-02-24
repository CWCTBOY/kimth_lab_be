package snust.kimth_lab.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity(name = "company")
public class Company {
  @JsonIgnore
  @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
  List<Project> companyProjects;
  @JsonIgnore
  @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
  List<Crew> companyCrews;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotNull
  @Column(name = "company_name")
  private String companyName;
  @NotNull
  @Column(name = "company_address")
  private String companyAddress;

  @Builder
  public Company(String companyName, String companyAddress) {
    this.companyName = companyName;
    this.companyAddress = companyAddress;
  }
}
