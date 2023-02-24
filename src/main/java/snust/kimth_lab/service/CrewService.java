package snust.kimth_lab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import snust.kimth_lab.dto.response.CompanyResDto;
import snust.kimth_lab.dto.response.CrewResDto;
import snust.kimth_lab.entity.Company;
import snust.kimth_lab.entity.Crew;
import snust.kimth_lab.repository.CompanyRepositoryInterface;
import snust.kimth_lab.repository.CrewRepositoryInterface;
import snust.kimth_lab.service.serviceInterface.CrewServiceInterface;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CrewService implements CrewServiceInterface {
  private final CompanyRepositoryInterface companyRepository;
  private final CrewRepositoryInterface crewRepository;

  @Autowired
  public CrewService(
    CompanyRepositoryInterface companyRepository,
    CrewRepositoryInterface crewRepository
  ) {
    this.companyRepository = companyRepository;
    this.crewRepository = crewRepository;
  }

  @Override
  public List<CrewResDto> getAuthorizedCompanyCrews(Long companyId) {
    Optional<Company> company = companyRepository.findById(companyId);
    if (company.isEmpty()) {
      return null;
    }
    List<Crew> authorizedCrewList = company.get().getCompanyCrews()
      .stream()
      .filter(Crew::isAuthorized)
      .collect(Collectors.toList());
    return authorizedCrewList
      .stream()
      .map(crew ->
        CrewResDto.builder()
          .id(crew.getId())
          .email(crew.getEmail())
          .name(crew.getName())
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
      ).collect(Collectors.toList());
  }

  @Override
  public List<CrewResDto> getUnauthorizedCompanyCrews(Long companyId) {
    Optional<Company> company = companyRepository.findById(companyId);
    if (company.isEmpty()) {
      return null;
    }
    List<Crew> authorizedCrewList = company.get().getCompanyCrews()
      .stream()
      .filter(crew -> !crew.isAuthorized())
      .collect(Collectors.toList());
    return authorizedCrewList
      .stream()
      .map(crew ->
        CrewResDto.builder()
          .id(crew.getId())
          .email(crew.getEmail())
          .name(crew.getName())
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
      ).collect(Collectors.toList());
  }

  @Override
  public Optional<Crew> findByJSessionId(
    String jSessionId,
    HttpServletRequest request
  ) {
    Long crewId = (Long) request.getSession().getAttribute(jSessionId);
    if (crewId == null) {
      return Optional.empty();
    }
    return crewRepository.findById(crewId);
  }

  @Override
  public Optional<Crew> findById(Long crewId) {
    return crewRepository.findById(crewId);
  }


  @Override
  public List<Crew> getCompanyCrewsByName(
    List<Crew> companyCrews,
    String crewName
  ) {
    return companyCrews.stream()
      .filter(crew ->
        crew.getName().equals(crewName) && crew.isAuthorized())
      .collect(Collectors.toList());
  }
}
