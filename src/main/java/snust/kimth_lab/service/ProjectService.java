package snust.kimth_lab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import snust.kimth_lab.dto.request.ProjectReqDto;
import snust.kimth_lab.dto.response.ParticipantsResDto;
import snust.kimth_lab.dto.response.ProjectResDto;
import snust.kimth_lab.entity.Company;
import snust.kimth_lab.entity.Participants;
import snust.kimth_lab.entity.Project;
import snust.kimth_lab.entity.ProjectRole;
import snust.kimth_lab.repository.CompanyRepositoryInterface;
import snust.kimth_lab.repository.ParticipantsRepositoryInterface;
import snust.kimth_lab.repository.ProjectRepositoryInterface;
import snust.kimth_lab.service.serviceInterface.ParticipantsServiceInterface;
import snust.kimth_lab.service.serviceInterface.ProjectServiceInterface;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProjectService implements ProjectServiceInterface {
  private final ParticipantsRepositoryInterface participantsRepository;
  private final CompanyRepositoryInterface companyRepository;
  private final ProjectRepositoryInterface projectRepository;
  private final ParticipantsServiceInterface participantsService;

  @Autowired
  public ProjectService(
    ParticipantsRepositoryInterface participantsRepository,
    CompanyRepositoryInterface companyRepository,
    ProjectRepositoryInterface projectRepository,
    ParticipantsServiceInterface participantsService
  ) {
    this.participantsRepository = participantsRepository;
    this.companyRepository = companyRepository;
    this.projectRepository = projectRepository;
    this.participantsService = participantsService;
  }

  @Override
  public Long addNewProject(
    ProjectReqDto projectReqDto,
    Company company
  ) {
    String floorPlanUrl = projectReqDto.getFloorPlan();
    String thumbnailUrl = projectReqDto.getThumbnail();
    Project newProject = Project.builder()
      .name(projectReqDto.getName())
      .company(company)
      .startDate(projectReqDto.getStartDate())
      .endDate(projectReqDto.getEndDate())
      .constructionClass(projectReqDto.getConstructionClass())
      .detailConstructionClass(projectReqDto.getDetailConstructionClass())
      .floorPlanUrl(floorPlanUrl)
      .thumbnailUrl(thumbnailUrl)
      .build();
    Participants participants = Participants.builder()
      .project(projectRepository.save(newProject))
      .crewId(projectReqDto.getManagerId())
      .projectRole(ProjectRole.MANAGER)
      .isPermitted(true)
      .build();
    return participantsRepository.save(participants).getId();
  }

  @Override
  public Long updateProject(
    Long projectId,
    ProjectReqDto projectReqDto
  ) {
    Optional<Project> project = projectRepository.findById(projectId);
    project.ifPresent(updatedProject -> {
      updatedProject.setName(projectReqDto.getName());
      updatedProject.setStartDate(projectReqDto.getStartDate());
      updatedProject.setEndDate(projectReqDto.getEndDate());
      updatedProject.setConstructionClass(projectReqDto.getConstructionClass());
      updatedProject.setDetailConstructionClass(projectReqDto.getDetailConstructionClass());
      updatedProject.setFloorPlanUrl(projectReqDto.getFloorPlan());
      updatedProject.setThumbnailUrl(projectReqDto.getThumbnail());
      projectRepository.save(updatedProject);
      List<Participants> participants = updatedProject.getParticipants();
      Optional<Participants> managerInfo = participants.stream()
        .filter(participant -> participant.getProjectRole().equals(ProjectRole.MANAGER))
        .findAny();
      managerInfo.ifPresent(info -> {
        Long managerParticipantsPk = info.getId();
        Optional<Participants> managerInstance = participantsRepository.findById(managerParticipantsPk);
        managerInstance.ifPresent(manager -> {
          manager.setCrewId(projectReqDto.getManagerId());
          participantsRepository.save(manager);
        });
      });
    });
    return projectId;
  }

  @Override
  public Optional<ProjectResDto> findByProjectId(Long projectId) {
    Optional<Project> project = projectRepository.findById(projectId);
    if (project.isEmpty()) {
      return Optional.empty();
    }
    return Optional.ofNullable(ProjectResDto.builder()
      .id(project.get().getId())
      .name(project.get().getName())
      .processRate(project.get().getProcessRate())
      .startDate(project.get().getStartDate())
      .endDate(project.get().getEndDate())
      .constructionClass(project.get().getConstructionClass())
      .detailConstructionClass(project.get().getDetailConstructionClass())
      .floorPlanUrl(project.get().getFloorPlanUrl())
      .thumbnailUrl(project.get().getThumbnailUrl())
      .participants(participantsService.findByProject(project.get()))
      .build());
  }

  @Override
  public List<ProjectResDto> findByCompanyId(Long companyId) {
    Optional<Company> company = companyRepository.findById(companyId);
    if (company.isEmpty()) {
      return null;
    }
    List<Project> companyProjects = company.get().getCompanyProjects();
    return companyProjects.stream()
      .map(
        project ->
          ProjectResDto.builder()
            .id(project.getId())
            .name(project.getName())
            .processRate(project.getProcessRate())
            .startDate(project.getStartDate())
            .endDate(project.getEndDate())
            .constructionClass(project.getConstructionClass())
            .detailConstructionClass(project.getDetailConstructionClass())
            .floorPlanUrl(project.getFloorPlanUrl())
            .thumbnailUrl(project.getThumbnailUrl())
            .participants(participantsService.findByProject(project))
            .build()
      ).collect(Collectors.toList());
  }

  @Override
  public List<ProjectResDto> findMyProjects(
    Long companyId,
    Long userId
  ) {
    Optional<Company> optionalCompany = companyRepository.findById(companyId);
    if (optionalCompany.isEmpty()) return null;
    List<Project> projectList = optionalCompany.get().getCompanyProjects();
    List<Project> myProjectList = projectList.stream()
      .filter(project -> {
          List<Participants> projectParticipants = project.getParticipants();
          Optional<Participants> me = projectParticipants.stream()
            .filter(participants -> participants.getCrewId().equals(userId))
            .findAny();
          return me.isPresent();
        }
      ).collect(Collectors.toList());
    return myProjectList.stream()
      .map(
        myProject ->
          ProjectResDto.builder()
            .id(myProject.getId())
            .name(myProject.getName())
            .processRate(myProject.getProcessRate())
            .startDate(myProject.getStartDate())
            .endDate(myProject.getEndDate())
            .constructionClass(myProject.getConstructionClass())
            .detailConstructionClass(myProject.getDetailConstructionClass())
            .floorPlanUrl(myProject.getFloorPlanUrl())
            .thumbnailUrl(myProject.getThumbnailUrl())
            .participants(participantsService.findByProject(myProject))
            .build()
      ).collect(Collectors.toList());
  }

  @Override
  public List<ProjectResDto> findExceptMyProjects(
    Long companyId,
    Long userId
  ) {
    Optional<Company> optionalCompany = companyRepository.findById(companyId);
    if (optionalCompany.isEmpty()) return null;
    List<Project> projectList = optionalCompany.get().getCompanyProjects();
    List<Project> myProjectList = projectList.stream()
      .filter(project -> {
          List<Participants> projectParticipants = project.getParticipants();
          Optional<Participants> me = projectParticipants.stream()
            .filter(participants -> participants.getCrewId().equals(userId))
            .findAny();
          return me.isEmpty();
        }
      ).collect(Collectors.toList());
    return myProjectList.stream()
      .map(
        myProject ->
          ProjectResDto.builder()
            .id(myProject.getId())
            .name(myProject.getName())
            .processRate(myProject.getProcessRate())
            .startDate(myProject.getStartDate())
            .endDate(myProject.getEndDate())
            .constructionClass(myProject.getConstructionClass())
            .detailConstructionClass(myProject.getDetailConstructionClass())
            .floorPlanUrl(myProject.getFloorPlanUrl())
            .thumbnailUrl(myProject.getThumbnailUrl())
            .participants(participantsService.findByProject(myProject))
            .build()
      ).collect(Collectors.toList());
  }

  @Override
  public boolean isParticipants(
    List<ParticipantsResDto> participants,
    Long crewId
  ) {
    for (ParticipantsResDto participant : participants) {
      if (participant.getCrewId().equals(crewId)) {
        return true;
      }
    }
    return false;
  }
}