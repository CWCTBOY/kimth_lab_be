package snust.kimth_lab.dto.response.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberResDto {
  private Long id;
  private String email;
  private String name;
  private String number;
  private String classification;
  private String company;
  private String companyAddress;

  @Builder
  public MemberResDto(Long id, String email, String name, String number, String classification, String company, String companyAddress) {
    this.id = id;
    this.email = email;
    this.name = name;
    this.number = number;
    this.classification = classification;
    this.company = company;
    this.companyAddress = companyAddress;
  }
}
