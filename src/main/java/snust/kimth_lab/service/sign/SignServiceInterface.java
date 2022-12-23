package snust.kimth_lab.service.sign;

import snust.kimth_lab.dto.request.sign.SignInReqDto;
import snust.kimth_lab.dto.request.sign.SignUpReqDto;
import snust.kimth_lab.entity.Member;

import java.util.Optional;

public interface SignServiceInterface {
  Long join(SignUpReqDto signupReqDto);

  Optional<Member> signIn(SignInReqDto signInReqDto);

  Optional<Member> isEmailDuplicated(SignUpReqDto signupReqDto);
}
