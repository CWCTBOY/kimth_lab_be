package snust.kimth_lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import snust.kimth_lab.entity.Member;

import java.util.Optional;

public interface MemberRepositoryInterface extends JpaRepository<Member, Long> {
  Optional<Member> findByEmail(String email);
}
