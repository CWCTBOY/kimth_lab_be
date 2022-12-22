package snust.kimth_lab.util.session;

import org.springframework.http.ResponseCookie;
import snust.kimth_lab.entity.Member;

import javax.servlet.http.HttpServletRequest;

public interface SessionManagerInterface {
  ResponseCookie createCookie(HttpServletRequest request, Member member);

  Member parse(HttpServletRequest request);
}
