package snust.kimth_lab.dto.request.sign;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignInReqDto {
  @ApiModelProperty(value = "this is email input for login form")
  private String email;
  @ApiModelProperty("this is password input for login form")
  private String password;

  @Builder
  public SignInReqDto(String email, String password) {
    this.email = email;
    this.password = password;
  }
}
