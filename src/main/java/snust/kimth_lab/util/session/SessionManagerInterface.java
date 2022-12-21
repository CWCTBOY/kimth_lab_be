package snust.kimth_lab.util.session;

import snust.kimth_lab.entity.Member;

import javax.servlet.http.HttpServletRequest;

public interface SessionManagerInterface {
  void set(HttpServletRequest request, Member member);
}
