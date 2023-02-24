package snust.kimth_lab.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snust.kimth_lab.entity.Role;

@Getter
@NoArgsConstructor
public class SessionResDto {
  private Long userId;
  private CompanyResDto companyInfo;
  private Role role;

  @Builder
  public SessionResDto(Long userId, CompanyResDto companyInfo, Role role) {
    this.userId = userId;
    this.companyInfo = companyInfo;
    this.role = role;
  }
}
