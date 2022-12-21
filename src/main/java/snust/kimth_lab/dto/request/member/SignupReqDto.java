package snust.kimth_lab.dto.request.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignupReqDto {
  private String email;
  private String password;
  private String name;
  private String number;
  private String classification;
  private String company;

  @Builder
  public SignupReqDto(String email, String password, String name, String number, String classification, String company) {
    this.email = email;
    this.password = password;
    this.name = name;
    this.number = number;
    this.classification = classification;
    this.company = company;
  }
}
