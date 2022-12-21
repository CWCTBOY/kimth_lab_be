package snust.kimth_lab.service.member;

import snust.kimth_lab.dto.request.member.SignupReqDto;
import snust.kimth_lab.entity.Member;

import java.util.Optional;

public interface MemberServiceInterface {
  Long join(SignupReqDto signupReqDto);

  Optional<Member> isEmailDuplicated(SignupReqDto signupReqDto);
}
