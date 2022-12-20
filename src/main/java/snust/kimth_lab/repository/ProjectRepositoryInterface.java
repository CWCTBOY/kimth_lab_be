package snust.kimth_lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import snust.kimth_lab.entity.Project;

@Repository
public interface ProjectRepositoryInterface extends JpaRepository<Project, Long> {
}
