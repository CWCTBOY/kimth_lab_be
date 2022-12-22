package snust.kimth_lab.dto.response.sign;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SignResDto {
  private final String message;
  private final Long id;

  @Builder
  public SignResDto(String message, Long id) {
    this.message = message;
    this.id = id;
  }
}
