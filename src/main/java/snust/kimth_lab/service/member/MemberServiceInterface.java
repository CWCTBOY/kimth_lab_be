package snust.kimth_lab.service.member;

import snust.kimth_lab.entity.Member;

public interface MemberServiceInterface {
  Long join(Member member);

  boolean isEmailDuplicated(Member member);
}
