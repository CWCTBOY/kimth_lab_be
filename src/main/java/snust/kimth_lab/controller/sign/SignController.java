package snust.kimth_lab.controller.sign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import snust.kimth_lab.dto.request.sign.SignInReqDto;
import snust.kimth_lab.dto.request.sign.SignUpReqDto;
import snust.kimth_lab.dto.response.sign.SignResDto;
import snust.kimth_lab.entity.Member;
import snust.kimth_lab.service.sign.SignServiceInterface;
import snust.kimth_lab.util.session.SessionManagerInterface;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;


@RequestMapping("/auth")
@RestController
public class SignController {
  private final SignServiceInterface signService;
  private final SessionManagerInterface sessionManager;

  @Autowired
  public SignController(SignServiceInterface signService, SessionManagerInterface sessionManager) {
    this.signService = signService;
    this.sessionManager = sessionManager;
  }

  @PostMapping("/sign-up")
  public ResponseEntity<SignResDto> join(@RequestBody SignUpReqDto signupReqDto, HttpServletRequest req) {
    Optional<Member> member = signService.isEmailDuplicated(signupReqDto);
    if (member.isPresent()) {
      SignResDto body = SignResDto.builder()
        .id(null)
        .message("email is already exist.")
        .build();
      return ResponseEntity
        .status(HttpStatus.valueOf(401))
        .contentType(MediaType.APPLICATION_JSON)
        .body(body);
    } else {
      SignResDto body = SignResDto.builder()
        .id(signService.join(signupReqDto))
        .message("new member created.")
        .build();
      return ResponseEntity
        .status(HttpStatus.valueOf(201))
        .contentType(MediaType.APPLICATION_JSON)
        .body(body);
    }
  }

  @PostMapping("/sign-in")
  public ResponseEntity<SignResDto> signIn(@RequestBody SignInReqDto signInReqDto, HttpServletRequest request, HttpServletResponse response) {
    Optional<Member> member = signService.signIn(signInReqDto);
    if (member.isPresent()) {
      ResponseCookie responseCookie = sessionManager.createCookie(request, member.get());
      SignResDto body = SignResDto.builder()
        .id(member.get().getId())
        .message("login success.")
        .build();
      return ResponseEntity
        .status(200)
        .header(HttpHeaders.SET_COOKIE, responseCookie.toString())
        .body(body);
    } else {
      SignResDto body = SignResDto.builder()
        .id(null)
        .message("invalid email or password.")
        .build();
      return ResponseEntity
        .status(401)
        .body(body);
    }
  }

  @PostMapping("/sign-out")
  public ResponseEntity<String> signOut() {
    return null;
  }
}
