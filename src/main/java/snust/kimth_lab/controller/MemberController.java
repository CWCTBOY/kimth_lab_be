package snust.kimth_lab.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import snust.kimth_lab.dto.member.SignInDto;
import snust.kimth_lab.entity.Member;

@RequestMapping("/member")
@RestController
public class MemberController {
  @PostMapping("/signup")
  public ResponseEntity<Long> signup(@RequestBody Member member) {
    return new ResponseEntity<>(1L, HttpStatus.CREATED);
  }

  @PostMapping("/signin")
  public ResponseEntity<String> signin(@RequestBody SignInDto signInDto) {
    return new ResponseEntity<>("token!", HttpStatus.OK);
  }
}
