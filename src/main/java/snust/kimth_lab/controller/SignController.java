package snust.kimth_lab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import snust.kimth_lab.dto.request.SignInReqDto;
import snust.kimth_lab.dto.request.SignUpReqDto;
import snust.kimth_lab.dto.response.CompanyResDto;
import snust.kimth_lab.dto.response.SignResDto;
import snust.kimth_lab.entity.Company;
import snust.kimth_lab.entity.Crew;
import snust.kimth_lab.service.serviceInterface.CompanyServiceInterface;
import snust.kimth_lab.service.serviceInterface.SessionServiceInterface;
import snust.kimth_lab.service.serviceInterface.SignServiceInterface;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RequestMapping("/auth")
@RestController
public class SignController {
  private final CompanyServiceInterface companyService;
  private final SignServiceInterface signService;
  private final SessionServiceInterface sessionService;

  @Autowired
  public SignController(
    CompanyServiceInterface companyService,
    SignServiceInterface signService,
    SessionServiceInterface sessionService
  ) {
    this.companyService = companyService;
    this.signService = signService;
    this.sessionService = sessionService;
  }


  @PostMapping("/sign-up") // 회원가입
  public ResponseEntity<SignResDto> join(@RequestBody SignUpReqDto signupReqDto) {
    Long crewId = signService.join(signupReqDto);
    if (crewId == null) {
      return ResponseEntity
        .status(HttpStatus.valueOf(201))
        .body(
          SignResDto.builder()
            .memberId(signService.join(signupReqDto))
            .message("new member created.")
            .build()
        );
    }
    return ResponseEntity
      .status(HttpStatus.valueOf(403))
      .body(
        SignResDto.builder()
          .message("failed to join")
          .build()
      );
  }

  @PostMapping("/sign-in") // 로그인
  public ResponseEntity<SignResDto> signIn(
    @RequestBody SignInReqDto signInReqDto,
    HttpServletRequest request
  ) {
    Optional<Crew> crew = signService.validateCrew(signInReqDto);
    if (crew.isEmpty()) {
      return ResponseEntity
        .status(HttpStatus.valueOf(404))
        .body(
          SignResDto.builder()
            .memberId(null)
            .message("member not found")
            .build()
        );
    }
    if (!crew.get().isAuthorized()) { // 회원가입 승인받지 않은 회원 필터링
      return ResponseEntity
        .status(HttpStatus.valueOf(403))
        .body(null);
    }
    ResponseCookie responseCookie = sessionService.createCookie(crew.get(), request);
    return ResponseEntity
      .status(HttpStatus.valueOf(200))
      .header(HttpHeaders.SET_COOKIE, responseCookie.toString())
      .body(
        SignResDto.builder()
          .memberId(crew.get().getId())
          .message("login success.")
          .build()
      );
  }

  @GetMapping("/sign-out") // 로그아웃
  public ResponseEntity<?> signOut(
    @CookieValue(
      value = "JSESSIONID"
    ) String jSessionId,
    HttpServletRequest request
  ) {
    boolean isSessionRemoved = sessionService.removeSession(jSessionId, request);
    if (isSessionRemoved) {
      return ResponseEntity
        .status(HttpStatus.valueOf(200))
        .body(null);
    }
    return ResponseEntity
      .status(HttpStatus.valueOf(401))
      .body(null);
  }

  //회원가입 전 유저 인증로직
  @GetMapping("/company-list") // DB에 저장된 회사의 정보 반환.
  public ResponseEntity<List<CompanyResDto>> validateCompany(@PathParam("companyName") String companyName) {
    List<Company> companies = companyService.findByCompanyName(companyName);
    if (companies.size() == 0) {
      return ResponseEntity
        .status(HttpStatus.valueOf(404))
        .body(null);
    }
    return ResponseEntity
      .status(HttpStatus.valueOf(200))
      .body(
        companies.stream().map(company ->
            CompanyResDto.builder()
              .companyId(company.getId())
              .companyName(company.getCompanyName())
              .companyAddress(company.getCompanyAddress())
              .build())
          .collect(Collectors.toList())
      );
  }

  @GetMapping("/email-validation") // 이메일 인증 코드보내기
  public ResponseEntity<?> validateEmail(@PathParam("email") String email) {
    System.out.println("email = " + email);
    Optional<Crew> member = signService.isEmailDuplicated(email);
    if (member.isPresent()) {
      return ResponseEntity
        .status(HttpStatus.valueOf(401))
        .body(null);
    }
    return ResponseEntity
      .status(HttpStatus.valueOf(200))
      .body(null);
  }

  @GetMapping("/code-validation") // 이메일 인증코드 검증
  public ResponseEntity<?> validateCode(@PathParam("code") String code) {
    System.out.println("code = " + code);
    return null;
  }
}
