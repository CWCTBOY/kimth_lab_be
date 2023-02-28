package snust.kimth_lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import snust.kimth_lab.entity.Participant;

public interface ParticipantsRepositoryInterface extends JpaRepository<Participant, Long> {
}
