package snust.kimth_lab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import snust.kimth_lab.dto.response.CompanyResDto;
import snust.kimth_lab.dto.response.CrewResDto;
import snust.kimth_lab.entity.Company;
import snust.kimth_lab.entity.Crew;
import snust.kimth_lab.service.serviceInterface.CompanyServiceInterface;
import snust.kimth_lab.service.serviceInterface.CrewServiceInterface;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/crew")
@RestController
public class CrewController {
  private final CrewServiceInterface crewService;
  private final CompanyServiceInterface companyService;

  @Autowired
  public CrewController(
    CrewServiceInterface crewService,
    CompanyServiceInterface companyService
  ) {
    this.crewService = crewService;
    this.companyService = companyService;
  }

  @GetMapping("/all/authorized") // 회사별 회원
  public ResponseEntity<List<CrewResDto>> getAuthorizedCrew(
    @PathParam("companyId") Long companyId
  ) {
    List<CrewResDto> crewResDtoList = crewService.getAuthorizedCompanyCrews(companyId);
    return ResponseEntity
      .status(HttpStatus.valueOf(200))
      .body(crewResDtoList);
  }

  @GetMapping("/all/non-authorized")
  public ResponseEntity<List<CrewResDto>> getUnauthorizedCrew(
    @PathParam("companyId") Long companyId
  ) {
    List<CrewResDto> crewResDtoList = crewService.getUnauthorizedCompanyCrews(companyId);
    return ResponseEntity
      .status(HttpStatus.valueOf(200))
      .body(crewResDtoList);
  }

  @GetMapping("/one")
  public ResponseEntity<CrewResDto> getCrewById(
    @PathParam("crewId") Long crewId
  ) {
    Optional<Crew> crew = crewService.findById(crewId);
    if (crew.isEmpty()) {
      return ResponseEntity
        .status(HttpStatus.valueOf(404))
        .body(null);
    }
    System.out.println(crew.toString());
    return ResponseEntity
      .status(HttpStatus.valueOf(200))
      .body(
        CrewResDto.builder()
          .id(crew.get().getId())
          .email(crew.get().getEmail())
          .name(crew.get().getName())
          .classification(crew.get().getClassification())
          .role(crew.get().getRole())
          .isAuthorized(crew.get().isAuthorized())
          .companyInfo(
            CompanyResDto.builder()
              .companyId(crew.get().getCompany().getId())
              .companyName(crew.get().getCompany().getCompanyName())
              .companyAddress(crew.get().getCompany().getCompanyAddress())
              .build()
          ).build());
  }

  @GetMapping("/company/name")
  public ResponseEntity<List<CrewResDto>> getCrewsByName(
    @PathParam("companyId") Long companyId,
    @PathParam("crewName") String crewName
  ) {
    Optional<Company> company = companyService.findByCompanyId(companyId);
    if (company.isEmpty()) {
      return ResponseEntity
        .status(HttpStatus.valueOf(404))
        .body(null);
    }
    List<Crew> companyCrews = company.get().getCompanyCrews();
    List<Crew> companyCrewsByName = crewService.getCompanyCrewsByName(companyCrews, crewName);
    return ResponseEntity
      .status(HttpStatus.valueOf(200))
      .body(companyCrewsByName.stream().map
          (
            crew -> CrewResDto.builder()
              .id(crew.getId())
              .name(crew.getName())
              .email(crew.getEmail())
              .number(crew.getNumber())
              .classification(crew.getClassification())
              .role(crew.getRole())
              .isAuthorized(crew.isAuthorized())
              .companyInfo(
                CompanyResDto.builder()
                  .companyId(crew.getCompany().getId())
                  .companyName(crew.getCompany().getCompanyName())
                  .companyAddress(crew.getCompany().getCompanyAddress())
                  .build()
              ).build()
          )
        .collect(Collectors.toList()));
  }
}
