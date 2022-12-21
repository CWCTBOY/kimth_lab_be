package snust.kimth_lab.util.session;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import snust.kimth_lab.entity.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@NoArgsConstructor
@Component
public class SessionManager implements SessionManagerInterface {
  @Override
  public void set(HttpServletRequest request, Member member) {
    HttpSession session = request.getSession(false);
    System.out.println("session is not present!");
    session.setAttribute("session_id", member);
    System.out.println(session.getAttribute("session_id").toString());

//    System.out.println("isSessionIdPresent? : " + isSessionPresent(request));
//    String randomSessionId = UUID.randomUUID().toString();
//    HttpSession session = request.getSession(false);
//    session.setAttribute("session_id", randomSessionId);
//    System.out.println("session.getId() = " + session.getAttribute("session_id"));
//    System.out.println(session.getAttribute("session_id").equals(randomSessionId));

  }

  public boolean isSessionPresent(HttpServletRequest request) {
    Optional<HttpSession> session = Optional.ofNullable(request.getSession());
    return session.isPresent();
  }

}
