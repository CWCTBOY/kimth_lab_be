package snust.kimth_lab.service.serviceInterface;

import snust.kimth_lab.dto.request.SignInReqDto;
import snust.kimth_lab.dto.request.SignUpReqDto;
import snust.kimth_lab.entity.Crew;

import java.util.Optional;

public interface SignServiceInterface {
  Long join(SignUpReqDto signupReqDto);

  Optional<Crew> validateCrew(SignInReqDto signInReqDto);

  boolean isCompanyPresent(Long companyId);

  boolean isEmailDuplicated(String email);
}
