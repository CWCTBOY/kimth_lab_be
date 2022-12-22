package snust.kimth_lab.controller.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import snust.kimth_lab.dto.response.session.SessionResDto;
import snust.kimth_lab.util.session.SessionManagerInterface;

import javax.servlet.http.HttpServletRequest;

@RestController
public class SessionController {
  SessionManagerInterface sessionManager;

  @Autowired
  public SessionController(SessionManagerInterface sessionManager) {
    this.sessionManager = sessionManager;
  }

  @GetMapping("/session-info")
  public ResponseEntity<SessionResDto> sessionInfo(HttpServletRequest request) {
    return null;
  }
}
