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
    Optional<Company> company = getCompany(signupReqDto);
    if (company.isEmpty()) {
      return null;
    }
    String encryptedPassword = encryptPassword(signupReqDto);
    Role role = selectRole(signupReqDto);
    Crew crew = Crew.builder()
      .company(company.get())
      .email(signupReqDto.getEmail())
      .password(encryptedPassword)
      .name(signupReqDto.getName())
      .number(signupReqDto.getNumber())
      .classification(signupReqDto.getClassification())
      .role(role)
      .isAuthorized(role.equals(Role.GUEST))
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

  private Role selectRole(SignUpReqDto signUpReqDto) {
    Role role;
    String classification = signUpReqDto.getClassification();
    switch (classification) {
      case "관리자":
        role = Role.COMPANY_ADMIN;
        break;
      case "게스트":
        role = Role.GUEST;
        break;
      default:
        role = Role.MEMBER;
        break;
    }
    return role;
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
