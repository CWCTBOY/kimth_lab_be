package snust.kimth_lab.dto.request.sign;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignUpReqDto {
  private String email;
  private String password;
  private String name;
  private String number;
  private String company;
  private String companyAddress;
  private String classification;

  @Builder
  public SignUpReqDto(String email, String password, String name, String number, String company, String companyAddress, String classification) {
    this.email = email;
    this.password = password;
    this.name = name;
    this.number = number;
    this.company = company;
    this.companyAddress = companyAddress;
    this.classification = classification;
  }
}
