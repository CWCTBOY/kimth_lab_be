package snust.kimth_lab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import snust.kimth_lab.dto.request.SignInReqDto;
import snust.kimth_lab.dto.request.SignUpReqDto;
import snust.kimth_lab.entity.Company;
import snust.kimth_lab.entity.Crew;
import snust.kimth_lab.entity.Role;
import snust.kimth_lab.repository.CompanyRepositoryInterface;
import snust.kimth_lab.repository.CrewRepositoryInterface;
import snust.kimth_lab.service.serviceInterface.SignServiceInterface;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.transaction.Transactional;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SignService implements SignServiceInterface {
  private final CrewRepositoryInterface crewRepository;
  private final CompanyRepositoryInterface companyRepository;

  @Autowired
  public SignService(
    CrewRepositoryInterface crewRepository,
    CompanyRepositoryInterface companyRepository
  ) {
    this.crewRepository = crewRepository;
    this.companyRepository = companyRepository;
  }


  @Override
  public Long join(SignUpReqDto signupReqDto) {
    Long companyId = signupReqDto.getCompanyId();
    Optional<Company> company = companyRepository.findById(companyId);
    if (company.isEmpty()) {
      return null;
    }
    if (!isPossibleToJoin(signupReqDto)) {
      return null;
    }
    Crew crew = Crew.builder()
      .company(company.get())
      .email(signupReqDto.getEmail())
      .password(encryptPassword(signupReqDto))
      .name(signupReqDto.getName())
      .number(signupReqDto.getNumber())
      .classification(signupReqDto.getClassification())
      .role(signupReqDto.getClassification().equals("관리자") ? Role.COMPANY_ADMIN : Role.USER)
      .build();
    return crewRepository.save(crew).getId();
  }

  @Override
  public Optional<Crew> validateCrew(SignInReqDto signInReqDto) {
    Optional<Crew> member = crewRepository.findByEmail(signInReqDto.getEmail());
    if (member.isPresent()) {
      String inComingCode = signInReqDto.getEmail() + signInReqDto.getPassword();
      String decryptedPassword = decryptPassword(member.get().getPassword());
      if (inComingCode.equals(decryptedPassword)) {
        return member;
      } else {
        return Optional.empty();
      }
    } else {
      return Optional.empty();
    }
  }

  @Override
  public Optional<Crew> isEmailDuplicated(String email) {
    return crewRepository.findByEmail(email);
  }

  private Optional<Company> getCompany(SignUpReqDto signUpReqDto) {
    Long companyId = signUpReqDto.getCompanyId();
    return companyRepository.findById(companyId);
  }

  private boolean isPossibleToJoin(SignUpReqDto signUpReqDto) {
    String classification = signUpReqDto.getClassification();
    boolean isAdminPresent = isAdminPresent(signUpReqDto);
    if (classification.equals("관리자")) {
      return !isAdminPresent;
    } else {
      return isAdminPresent;
    }
  }

  private boolean isAdminPresent(SignUpReqDto signUpReqDto) {
    Long companyId = signUpReqDto.getCompanyId();
    Optional<Company> company = companyRepository.findById(companyId);
    if (company.isPresent()) {
      List<Crew> companyCrewList = company.get().getCompanyCrews();
      Optional<Crew> companyAdmin = companyCrewList.stream().filter(crew ->
        crew.getRole().equals(Role.COMPANY_ADMIN)
      ).findAny();
      return companyAdmin.isPresent();
    }
    return false;
  }

  private String encryptPassword(SignUpReqDto signUpReqDto) {
    String alg = "AES/CBC/PKCS5Padding";
    String aesIv = "0123456789abcdef";
    String aesKey = "abcdefghabcdefghabcdefghabcdefgh";
    String encryptedCode = "";
    String target = signUpReqDto.getEmail() + signUpReqDto.getPassword();
    try {
      Cipher cipher = Cipher.getInstance(alg);
      SecretKeySpec keySpec = new SecretKeySpec(aesKey.getBytes(), "AES");
      IvParameterSpec ivParameterSpec = new IvParameterSpec(aesIv.getBytes());
      cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParameterSpec);
      byte[] encrypted1 = cipher.doFinal(target.getBytes("UTF-8"));
      encryptedCode = Base64.getEncoder().encodeToString(encrypted1);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return encryptedCode;
  }

  private String decryptPassword(String encryptedCode) {
    String alg = "AES/CBC/PKCS5Padding";
    String aesIv = "0123456789abcdef";
    String aesKey = "abcdefghabcdefghabcdefghabcdefgh";
    String decryptedPassword = "";
    try {
      Cipher cipher = Cipher.getInstance(alg);
      SecretKeySpec keySpec = new SecretKeySpec(aesKey.getBytes(), "AES");
      IvParameterSpec ivParameterSpec = new IvParameterSpec(aesIv.getBytes());
      cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParameterSpec);
      byte[] decodedBytes = Base64.getDecoder().decode(encryptedCode);
      byte[] decrypted = cipher.doFinal(decodedBytes);
      decryptedPassword = new String(decrypted);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return decryptedPassword;
  }
}
