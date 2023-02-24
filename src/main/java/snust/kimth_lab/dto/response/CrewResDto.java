package snust.kimth_lab.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snust.kimth_lab.entity.Role;

@Getter
@NoArgsConstructor
public class CrewResDto {
  private Long id;
  private String email;
  private String name;
  private String number;
  private String classification;
  private CompanyResDto companyInfo;
  private Role role;
  private boolean isAuthorized;

  @Builder
  public CrewResDto(Long id, String email, String name, String number, String classification, CompanyResDto companyInfo, Role role, boolean isAuthorized) {
    this.id = id;
    this.email = email;
    this.name = name;
    this.number = number;
    this.classification = classification;
    this.companyInfo = companyInfo;
    this.role = role;
    this.isAuthorized = isAuthorized;
  }
}
