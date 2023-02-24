package snust.kimth_lab.service.serviceInterface;

import snust.kimth_lab.dto.response.ParticipantsResDto;
import snust.kimth_lab.entity.Project;

import java.util.List;

public interface ParticipantsServiceInterface {
  List<ParticipantsResDto> findByProject(Project project);

  Long addParticipants(Project project, Long crewId);

  void removeManagerParticipants();

  Long addNormalParticipants();

  void removeNormalParticipants();
}
