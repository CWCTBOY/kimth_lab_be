package snust.kimth_lab.dto.response.member;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignupResDto {
  private String message;
  private Long id;

  @Builder
  public SignupResDto(String message, Long id) {
    this.message = message;
    this.id = id;
  }
}
