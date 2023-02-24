package snust.kimth_lab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import snust.kimth_lab.dto.request.ProjectReqDto;
import snust.kimth_lab.dto.response.ProjectResDto;
import snust.kimth_lab.entity.Company;
import snust.kimth_lab.service.serviceInterface.CompanyServiceInterface;
import snust.kimth_lab.service.serviceInterface.ProjectServiceInterface;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@RequestMapping("/project")
@RestController
public class ProjectController {
  private final ProjectServiceInterface projectService;
  private final CompanyServiceInterface companyService;

  @Autowired
  public ProjectController(
    ProjectServiceInterface projectService,
    CompanyServiceInterface companyService
  ) {
    this.projectService = projectService;
    this.companyService = companyService;
  }

  @PostMapping("/new")
  public ResponseEntity<Long> newProject(
    @RequestBody ProjectReqDto projectReqDto
  ) {
    Long companyId = projectReqDto.getCompanyId();
    Optional<Company> company = companyService.findByCompanyId(companyId);
    if (company.isEmpty()) {
      return ResponseEntity
        .status(HttpStatus.valueOf(404))
        .body(null);
    }
    Long projectId = projectService.addNewProject(projectReqDto, company.get());
    return ResponseEntity
      .status(HttpStatus.valueOf(201))
      .body(projectId);
  }

  @PutMapping("/edit/{projectId}")
  public ResponseEntity<?> editProject(
    @PathVariable("projectId") Long projectId,
    @RequestBody ProjectReqDto projectReqDto
  ) {
    return ResponseEntity
      .status(HttpStatus.valueOf(200))
      .body(projectService.updateProject(projectId, projectReqDto));
  }

  @GetMapping("/all")
  public ResponseEntity<List<ProjectResDto>> getAllProjects(@PathParam("companyId") Long companyId) {
    return ResponseEntity
      .status(HttpStatus.valueOf(200))
      .body(projectService.findByCompanyId(companyId));
  }

  @GetMapping("/all/attendance")
  public ResponseEntity<List<ProjectResDto>> getMyProjects(
    @PathParam("companyId") Long companyId,
    @PathParam("userId") Long userId
  ) {
    return ResponseEntity
      .status(HttpStatus.valueOf(200))
      .body(projectService.findMyProjects(companyId, userId));
  }

  @GetMapping("/all/nonattendance")
  public ResponseEntity<List<ProjectResDto>> getExceptMyProjects(
    @PathParam("companyId") Long companyId,
    @PathParam("userId") Long userId
  ) {
    return ResponseEntity
      .status(HttpStatus.valueOf(200))
      .body(projectService.findExceptMyProjects(companyId, userId));
  }

  @GetMapping
  public ResponseEntity<ProjectResDto> getSpecificProject(
    @PathParam("projectId") Long projectId,
    @PathVariable("userId") Long userId
  ) {
    Optional<ProjectResDto> projectResDto = projectService.findByProjectId(projectId);
    if (projectResDto.isEmpty()) {
      return ResponseEntity
        .status(HttpStatus.valueOf(404))
        .body(null);
    }
    return ResponseEntity
      .status(HttpStatus.valueOf(200))
      .body(projectResDto.get());
  }
}
