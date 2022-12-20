package snust.kimth_lab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import snust.kimth_lab.dto.member.SignInDto;
import snust.kimth_lab.entity.Member;
import snust.kimth_lab.service.member.MemberServiceInterface;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/member")
@RestController
public class MemberController {
  private final MemberServiceInterface memberService;

  @Autowired
  public MemberController(MemberServiceInterface memberService) {
    this.memberService = memberService;
  }

  @PostMapping("/signup")
  public ResponseEntity<Long> signup(@RequestBody Member member) {
    return null;
  }

  @PostMapping("/signin")
  public ResponseEntity<String> signin(@RequestBody SignInDto signInDto) {
    return new ResponseEntity<>("token!", HttpStatus.OK);
  }
}
