package snust.kimth_lab.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignInReqDto {
  private String email;
  private String password;

  @Builder
  public SignInReqDto(String email, String password) {
    this.email = email;
    this.password = password;
  }
}
