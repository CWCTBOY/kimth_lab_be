package snust.kimth_lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import snust.kimth_lab.entity.Crew;

import java.util.Optional;

public interface CrewRepositoryInterface extends JpaRepository<Crew, Long> {
  Optional<Crew> findByEmail(String email);
}
