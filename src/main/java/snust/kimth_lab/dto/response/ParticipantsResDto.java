package snust.kimth_lab.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snust.kimth_lab.entity.ProjectRole;

@Getter
@NoArgsConstructor
public class ParticipantsResDto {
  private Long crewId;
  private String email;
  private String name;
  private String number;
  private ProjectRole projectRole;
  private String classification;

  @Builder
  public ParticipantsResDto(Long crewId, String email, String name, String number, ProjectRole projectRole, String classification) {
    this.crewId = crewId;
    this.email = email;
    this.name = name;
    this.number = number;
    this.projectRole = projectRole;
    this.classification = classification;
  }
}
