package snust.kimth_lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import snust.kimth_lab.entity.Company;

public interface CompanyRepositoryInterface extends JpaRepository<Company, Long> {
}
