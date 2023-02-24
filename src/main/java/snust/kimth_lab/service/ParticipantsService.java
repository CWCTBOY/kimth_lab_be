package snust.kimth_lab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import snust.kimth_lab.dto.response.ParticipantsResDto;
import snust.kimth_lab.entity.Crew;
import snust.kimth_lab.entity.Participants;
import snust.kimth_lab.entity.Project;
import snust.kimth_lab.repository.CrewRepositoryInterface;
import snust.kimth_lab.service.serviceInterface.ParticipantsServiceInterface;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ParticipantsService implements ParticipantsServiceInterface {
  private final CrewRepositoryInterface crewRepository;

  @Autowired
  public ParticipantsService(
    CrewRepositoryInterface crewRepository
  ) {
    this.crewRepository = crewRepository;
  }

  @Override
  public List<ParticipantsResDto> findByProject(Project project) {
    List<Participants> participants = project.getParticipants();
    return participants
      .stream()
      .filter(Participants::isPermitted)
      .map(participant -> {
        Optional<Crew> crew = crewRepository.findById(participant.getCrewId());
        if (crew.isEmpty()) {
          return null;
        }
        return ParticipantsResDto.builder()
          .crewId(crew.get().getId())
          .projectRole(participant.getProjectRole())
          .name(crew.get().getName())
          .email(crew.get().getEmail())
          .number(crew.get().getNumber())
          .classification(crew.get().getClassification())
          .build();
      })
      .collect(Collectors.toList());
  }

  @Override
  public Long addParticipants(Project project, Long crewId) {
    return null;
  }

  @Override
  public void removeManagerParticipants() {

  }

  @Override
  public Long addNormalParticipants() {
    return null;
  }

  @Override
  public void removeNormalParticipants() {

  }
}
