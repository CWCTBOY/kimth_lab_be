package snust.kimth_lab.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "session")
public class Session {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "session_id")
  private String sessionId;

  @Builder
  public Session(String sessionId) {
    this.sessionId = sessionId;
  }
}
