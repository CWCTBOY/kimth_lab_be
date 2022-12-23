package snust.kimth_lab.controller.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import snust.kimth_lab.dto.response.member.MemberResDto;
import snust.kimth_lab.entity.Member;
import snust.kimth_lab.repository.MemberRepositoryInterface;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

@RestController
public class SessionController implements Serializable {
  MemberRepositoryInterface memberRepository;

  @Autowired
  public SessionController(MemberRepositoryInterface memberRepository) {
    this.memberRepository = memberRepository;
  }

  @GetMapping("/session-info")
  public ResponseEntity<MemberResDto> sessionInfo(@CookieValue(value = "JSESSIONID", required = false) String jSessionId, HttpServletRequest request) {
    if (jSessionId == null) {
      return ResponseEntity
        .status(401)
        .body(null);
    }
    Long memberId = (Long) request.getSession().getAttribute(jSessionId);
    Member member = memberRepository.findById(memberId).orElse(null);
    if (member == null) {
      return ResponseEntity.status(403).body(null); // 발생할 일 없음
    }
    return ResponseEntity
      .status(200)
      .body(MemberResDto.builder()
        .id(member.getId())
        .email(member.getEmail())
        .name(member.getName())
        .number(member.getNumber())
        .classification(member.getClassification())
        .company(member.getCompany())
        .companyAddress(member.getCompanyAddress())
        .build());
  }
}
