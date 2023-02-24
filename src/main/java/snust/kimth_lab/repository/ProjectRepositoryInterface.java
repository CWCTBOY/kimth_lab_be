package snust.kimth_lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import snust.kimth_lab.entity.Project;

public interface ProjectRepositoryInterface extends JpaRepository<Project, Long> {
}
