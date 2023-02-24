package snust.kimth_lab.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignResDto {
  private Long memberId;
  private String message;

  @Builder
  public SignResDto(Long memberId, String message) {
    this.memberId = memberId;
    this.message = message;
  }
}
