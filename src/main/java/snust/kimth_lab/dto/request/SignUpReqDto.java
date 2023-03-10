package snust.kimth_lab.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignUpReqDto {
  private Long companyId;
  private String email;
  private String password;
  private String name;
  private String number;
  private String classification;

  @Builder
  public SignUpReqDto(Long companyId, String email, String password, String name, String number, String classification) {
    this.companyId = companyId;
    this.email = email;
    this.password = password;
    this.name = name;
    this.number = number;
    this.classification = classification;
  }
}
