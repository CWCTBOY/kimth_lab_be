package snust.kimth_lab.entity;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
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
  @NotNull
  @Column(name = "company_address")
  private String companyAddress;

  @Builder
  public Member(String email, String password, String name, String number, String classification, String company, String companyAddress) {
    this.email = email;
    this.password = password;
    this.name = name;
    this.number = number;
    this.classification = classification;
    this.company = company;
    this.companyAddress = companyAddress;
  }
}
