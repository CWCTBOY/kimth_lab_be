package snust.kimth_lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import snust.kimth_lab.entity.Member;

@Repository
public interface MemberRepositoryInterface extends JpaRepository<Member, Long> {
}
