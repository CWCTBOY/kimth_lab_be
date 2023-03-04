package snust.kimth_lab.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TextResDto {
  private String message;

  @Builder
  public TextResDto(
    String message
  ) {
    this.message = message;
  }
}
