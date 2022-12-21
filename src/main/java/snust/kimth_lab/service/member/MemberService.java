package snust.kimth_lab.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import snust.kimth_lab.dto.request.member.SignupReqDto;
import snust.kimth_lab.entity.Member;
import snust.kimth_lab.repository.MemberRepositoryInterface;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class MemberService implements MemberServiceInterface {
  private final MemberRepositoryInterface memberRepository;

  @Autowired
  public MemberService(MemberRepositoryInterface memberRepository) {
    this.memberRepository = memberRepository;
  }

  @Override
  public Long join(SignupReqDto signupReqDto) {
    Member member = Member.builder()
      .email(signupReqDto.getEmail())
      .password(signupReqDto.getPassword())
      .name(signupReqDto.getName())
      .number(signupReqDto.getNumber())
      .company(signupReqDto.getCompany())
      .classification(signupReqDto.getClassification())
      .build();
    return memberRepository.save(member).getId();
  }

  @Override
  public Optional<Member> isEmailDuplicated(SignupReqDto signupReqDto) {
    String email = signupReqDto.getEmail();
    Optional<Member> member = memberRepository.findByEmail(email);
    System.out.println("member = " + member);
    return member;
  }
}
