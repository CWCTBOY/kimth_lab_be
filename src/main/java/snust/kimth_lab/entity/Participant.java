package snust.kimth_lab.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@Entity(name = "participant")
public class Participant {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;
  @NotNull
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "project_id")
  private Project project;
  @NotNull
  @Column(name = "crew_id")
  private Long crewId;
  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(name = "project_role")
  private ProjectRole projectRole;

  @Builder
  public Participant(Project project, Long crewId, ProjectRole projectRole) {
    this.project = project;
    this.crewId = crewId;
    this.projectRole = projectRole;
  }
}
