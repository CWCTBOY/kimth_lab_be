package snust.kimth_lab.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import snust.kimth_lab.entity.Member;
import snust.kimth_lab.repository.MemberRepositoryInterface;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MemberService implements MemberServiceInterface {
  private final MemberRepositoryInterface memberRepository;

  @Autowired
  public MemberService(MemberRepositoryInterface memberRepository) {
    this.memberRepository = memberRepository;
  }

  @Override
  public Long join(Member member) {
    return memberRepository.save(member).getId();
  }

  @Override
  public boolean isEmailDuplicated(Member member) {
    String email = member.getEmail();
    List<Member> memberList = memberRepository.findAll();
    return memberList.stream().anyMatch((item) -> item.getEmail().equals(email));
  }
}
