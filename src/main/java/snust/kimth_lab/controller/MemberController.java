package snust.kimth_lab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import snust.kimth_lab.dto.request.member.SignInDto;
import snust.kimth_lab.dto.request.member.SignupReqDto;
import snust.kimth_lab.dto.response.member.SignupResDto;
import snust.kimth_lab.entity.Member;
import snust.kimth_lab.service.member.MemberServiceInterface;

import java.util.Optional;

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
  public ResponseEntity<SignupResDto> signup(@RequestBody SignupReqDto signupReqDto) {
    Optional<Member> member = memberService.isEmailDuplicated(signupReqDto);
    if (member.isPresent()) {
      SignupResDto body = SignupResDto.builder()
        .id(null)
        .message("email is already exist.")
        .build();
      return ResponseEntity
        .status(HttpStatus.valueOf(401))
        .contentType(MediaType.APPLICATION_JSON)
        .body(body);
    } else {
      SignupResDto body = SignupResDto.builder()
        .id(memberService.join(signupReqDto))
        .message("new member created.")
        .build();
      return ResponseEntity
        .status(HttpStatus.valueOf(201))
        .contentType(MediaType.APPLICATION_JSON)
        .body(body);
    }
  }

  @PostMapping("/signin")
  public ResponseEntity<String> signin(@RequestBody SignInDto signInDto) {
    return new ResponseEntity<>("token!", HttpStatus.OK);
  }
}
