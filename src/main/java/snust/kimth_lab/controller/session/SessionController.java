package snust.kimth_lab.controller.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import snust.kimth_lab.dto.response.session.SessionResDto;
import snust.kimth_lab.util.session.SessionManagerInterface;

@RestController
public class SessionController {
  SessionManagerInterface sessionManager;

  @Autowired
  public SessionController(SessionManagerInterface sessionManager) {
    this.sessionManager = sessionManager;
  }

  @GetMapping(value = "/session-info", headers = "HEADER")
  public ResponseEntity<SessionResDto> sessionInfo(@RequestHeader HttpHeaders header) {
    System.out.println("header.toString() = " + header.toString());
    return null;
  }
}
