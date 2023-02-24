package snust.kimth_lab.service.serviceInterface;

import snust.kimth_lab.dto.response.CrewResDto;
import snust.kimth_lab.entity.Crew;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public interface CrewServiceInterface {
  Optional<Crew> findByJSessionId(String jSessionId, HttpServletRequest request);

  Optional<Crew> findById(Long crewId);

  List<CrewResDto> getAuthorizedCompanyCrews(Long companyId);

  List<CrewResDto> getUnauthorizedCompanyCrews(Long companyId);

  List<Crew> getCompanyCrewsByName(List<Crew> companyCrews, String crewName);
}
