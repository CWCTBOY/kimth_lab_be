package snust.kimth_lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import snust.kimth_lab.entity.Session;

public interface SessionRepositoryInterface extends JpaRepository<Session, Long> {
}
