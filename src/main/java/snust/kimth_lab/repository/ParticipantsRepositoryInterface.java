package snust.kimth_lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import snust.kimth_lab.entity.Participants;

public interface ParticipantsRepositoryInterface extends JpaRepository<Participants, Long> {
}
