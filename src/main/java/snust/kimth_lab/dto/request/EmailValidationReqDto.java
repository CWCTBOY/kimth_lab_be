package snust.kimth_lab.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EmailValidationReqDto {
  private String email;
  private String code;

  @Builder
  public EmailValidationReqDto(
    String email,
    String code
  ) {
    this.email = email;
    this.code = code;
  }
}
