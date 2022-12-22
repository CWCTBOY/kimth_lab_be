package snust.kimth_lab.dto.response.sign;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SignUpResDto {
  private final String message;
  private final Long id;

  @Builder
  public SignUpResDto(String message, Long id) {
    this.message = message;
    this.id = id;
  }
}
