package snust.kimth_lab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import snust.kimth_lab.entity.Company;
import snust.kimth_lab.repository.CompanyRepositoryInterface;
import snust.kimth_lab.service.serviceInterface.CompanyServiceInterface;

import javax.transaction.Transactional;
import java.net.URLDecoder;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CompanyService implements CompanyServiceInterface {
  private final CompanyRepositoryInterface companyRepository;

  @Autowired
  public CompanyService(CompanyRepositoryInterface companyRepository) {
    this.companyRepository = companyRepository;
  }

  @Override
  public Optional<Company> findByCompanyId(Long companyId) {
    return companyRepository.findById(companyId);
  }

  @Override
  public List<Company> findByCompanyName(String companyName) {
    return companyRepository
      .findAll()
      .stream()
      .filter(
        company ->
          company
            .getCompanyName()
            .contains(URLDecoder.decode(companyName)))
      .collect(Collectors.toList());
  }
}
