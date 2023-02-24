package snust.kimth_lab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import snust.kimth_lab.dto.response.CompanyResDto;
import snust.kimth_lab.dto.response.SessionResDto;
import snust.kimth_lab.entity.Company;
import snust.kimth_lab.entity.Crew;
import snust.kimth_lab.service.serviceInterface.CrewServiceInterface;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Optional;

@RestController
public class SessionController implements Serializable {
  private final CrewServiceInterface crewService;

  @Autowired
  public SessionController(CrewServiceInterface crewService) {
    this.crewService = crewService;
  }

  @GetMapping("/session-info")
  public ResponseEntity<SessionResDto> sessionInfo(
    @CookieValue(
      value = "JSESSIONID",
      required = false
    ) String jSessionId,
    HttpServletRequest request
  ) {
    if (jSessionId == null) {
      return ResponseEntity
        .status(HttpStatus.valueOf(401))
        .body(null);
    }
    Optional<Crew> crew = crewService.findByJSessionId(jSessionId, request);
    if (crew.isEmpty()) {
      return ResponseEntity
        .status(HttpStatus.valueOf(404))
        .body(null);
    }
    if (!crew.get().isAuthorized()) {
      return ResponseEntity
        .status(HttpStatus.valueOf(403))
        .body(null);
    }
    Company company = crew.get().getCompany();
    return ResponseEntity
      .status(200)
      .body(SessionResDto.builder()
        .userId(crew.get().getId())
        .role(crew.get().getRole())
        .companyInfo(
          CompanyResDto.builder()
            .companyId(company.getId())
            .companyName(company.getCompanyName())
            .companyAddress(company.getCompanyAddress())
            .build()
        )
        .build());
  }
}

