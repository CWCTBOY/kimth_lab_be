package snust.kimth_lab.service.serviceInterface;

import snust.kimth_lab.dto.request.ProjectReqDto;
import snust.kimth_lab.dto.response.ParticipantsResDto;
import snust.kimth_lab.dto.response.ProjectResDto;
import snust.kimth_lab.entity.Company;

import java.util.List;
import java.util.Optional;

public interface ProjectServiceInterface {
  Long addNewProject(ProjectReqDto projectReqDto, Company company);

  Long updateProject(Long projectId, ProjectReqDto projectReqDto);

  Optional<ProjectResDto> findByProjectId(Long projectId);

  List<ProjectResDto> findByCompanyId(Long companyId);

  List<ProjectResDto> findMyProjects(Long companyId, Long userId);

  List<ProjectResDto> findExceptMyProjects(Long companyId, Long userId);

  boolean isParticipants(List<ParticipantsResDto> participants, Long crewId);
}
