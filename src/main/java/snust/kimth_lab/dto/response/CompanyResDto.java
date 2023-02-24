package snust.kimth_lab.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CompanyResDto {
  private Long companyId;
  private String companyName;
  private String companyAddress;

  @Builder
  public CompanyResDto(Long companyId, String companyName, String companyAddress) {
    this.companyId = companyId;
    this.companyName = companyName;
    this.companyAddress = companyAddress;
  }
}
