package snust.kimth_lab.dto.response.session;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SessionResDto {

  private Long id;
  private String email;
  private String password;
  private String name;
  private String number;
  private String classification;
  private String company;
  private String companyAddress;

  @Builder
  public SessionResDto(Long id, String email, String password, String name, String number, String classification, String company, String companyAddress) {
    this.id = id;
    this.email = email;
    this.password = password;
    this.name = name;
    this.number = number;
    this.classification = classification;
    this.company = company;
    this.companyAddress = companyAddress;
  }
}
