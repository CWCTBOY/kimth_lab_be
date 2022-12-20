package snust.kimth_lab.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import snust.kimth_lab.entity.Member;
import snust.kimth_lab.entity.Project;

import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
public class MemberDto extends Member {
  @JsonIgnore
  @OneToMany(mappedBy = "userId")
  private List<Project> myProject;

  @Builder
  public MemberDto(List<Project> myProject) {
    super();
    this.myProject = myProject;
  }
}
