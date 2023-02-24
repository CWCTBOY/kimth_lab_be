package snust.kimth_lab.service.serviceInterface;

import snust.kimth_lab.entity.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyServiceInterface {
  Optional<Company> findByCompanyId(Long companyId);

  List<Company> findByCompanyName(String companyName);
}
