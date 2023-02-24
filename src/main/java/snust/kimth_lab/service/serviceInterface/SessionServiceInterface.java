package snust.kimth_lab.service.serviceInterface;

import org.springframework.http.ResponseCookie;
import snust.kimth_lab.entity.Crew;

import javax.servlet.http.HttpServletRequest;

public interface SessionServiceInterface {
  ResponseCookie createCookie(Crew crew, HttpServletRequest request);

  boolean removeSession(String jSessionId, HttpServletRequest request);
}
