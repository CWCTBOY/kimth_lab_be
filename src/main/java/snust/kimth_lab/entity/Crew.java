package snust.kimth_lab.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@Entity(name = "crew")
public class Crew {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotNull
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "company_id")
  private Company company;
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
  @Enumerated(EnumType.STRING)
  @Column(name = "role")
  private Role role;
  @NotNull
  @Column(name = "is_authorized", columnDefinition = "BOOLEAN DEFAULT false") // 나중에 false로 바꿔야함
  private boolean isAuthorized;

  @Builder
  public Crew(Company company, String email, String password, String name, String number, String classification, Role role, boolean isAuthorized) {
    this.company = company;
    this.email = email;
    this.password = password;
    this.name = name;
    this.number = number;
    this.classification = classification;
    this.role = role;
    this.isAuthorized = isAuthorized;
  }
}
