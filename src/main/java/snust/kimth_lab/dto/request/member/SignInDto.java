package snust.kimth_lab.dto.request.member;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SignInDto {
  @ApiModelProperty(value = "this is email input for signin form")
  private final String email;
  @ApiModelProperty("this is password input for signin form")
  private final String password;

  @Builder
  public SignInDto(String email, String password) {
    this.email = email;
    this.password = password;
  }
}
