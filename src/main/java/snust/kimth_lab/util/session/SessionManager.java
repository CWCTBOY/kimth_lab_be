package snust.kimth_lab.util.session;

import lombok.NoArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import snust.kimth_lab.entity.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@NoArgsConstructor
@Component
public class SessionManager implements SessionManagerInterface {

  @Override
  public ResponseCookie createCookie(HttpServletRequest request, Member member) {
    String mySessionId = create(request, member);
    return ResponseCookie.from("JSESSIONID", mySessionId)
      .httpOnly(true)
      .secure(true)
      .path("/")
      .sameSite("none")
      .domain("localhost")
      .maxAge(30)
      .build();
  }

  public String create(HttpServletRequest request, Member member) {
    HttpSession session = request.getSession();
    String sessionId = session.getId();
    session.setAttribute(sessionId, member.toString());
    return sessionId;
  }

  @Override
  public Member parse(HttpServletRequest request) {
    return null;
  }
}
