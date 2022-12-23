package snust.kimth_lab.service.sign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import snust.kimth_lab.dto.request.sign.SignInReqDto;
import snust.kimth_lab.dto.request.sign.SignUpReqDto;
import snust.kimth_lab.entity.Member;
import snust.kimth_lab.repository.MemberRepositoryInterface;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class SignService implements SignServiceInterface {
  private final MemberRepositoryInterface memberRepository;

  @Autowired
  public SignService(MemberRepositoryInterface memberRepository) {
    this.memberRepository = memberRepository;
  }

  @Override
  public Long join(SignUpReqDto signupReqDto) {
    Member member = Member.builder()
      .email(signupReqDto.getEmail())
      .password(signupReqDto.getPassword())
      .name(signupReqDto.getName())
      .number(signupReqDto.getNumber())
      .company(signupReqDto.getCompany())
      .companyAddress(signupReqDto.getCompanyAddress())
      .classification(signupReqDto.getClassification())
      .build();
    return memberRepository.save(member).getId();
  }

  @Override
  public Optional<Member> signIn(SignInReqDto signInReqDto) {
    Optional<Member> member = memberRepository.findByEmail(signInReqDto.getEmail());
    if (member.isPresent()) {
      if (member.get().getPassword().equals(signInReqDto.getPassword())) {
        return member;
      } else {
        return Optional.empty();
      }
    } else {
      return Optional.empty();
    }
  }

  @Override
  public Optional<Member> isEmailDuplicated(SignUpReqDto signupReqDto) {
    return memberRepository.findByEmail(signupReqDto.getEmail());
  }
}
