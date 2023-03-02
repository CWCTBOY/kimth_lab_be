package snust.kimth_lab.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.util.Random;

@Slf4j
@Service
@Transactional
public class EmailService {
  private final String code = createCode();
  private final JavaMailSender javaMailSender;
  @Value("${spring.mail.username}")
  private String from;
  @Value("${spring.mail.host}")
  private String host;
  @Value("${spring.mail.port}")
  private String port;
  @Value("${spring.mail.password}")
  private String password;

  EmailService(JavaMailSender javaMailSender) {
    this.javaMailSender = javaMailSender;
  }

  public String createCode() {
    Random random = new Random();
    StringBuffer key = new StringBuffer();
    for (int i = 0; i < 8; i++) {
      int index = random.nextInt(3);
      switch (index) {
        case 0:
          key.append((char) ((int) random.nextInt(26) + 97));
          break;
        case 1:
          key.append((char) ((int) random.nextInt(26) + 65));
          break;
        case 2:
          key.append(random.nextInt(9));
          break;
      }
    }
    return key.toString();
  }

  private MimeMessage createMessage(String to) throws MessagingException, UnsupportedEncodingException {
    log.info("to: " + to);
    log.info("code: " + code);
    MimeMessage message = javaMailSender.createMimeMessage();
    message.addRecipients(MimeMessage.RecipientType.TO, to);
    message.setSubject(to + "님, SMART BUILDING SUPERVISION PLATFORM 회원 인증코드입니다.");
    String msg = "";
    msg += "<h1 style=\"font-size: 30px; padding-right: 30px; padding-left: 30px;\">이메일 주소 확인</h1>";
    msg += "<p style=\"font-size: 17px; padding-right: 30px; padding-left: 30px;\">아래 확인 코드를 회원가입 화면에서 입력해주세요.</p>";
    msg += "<div style=\"padding-right: 30px; padding-left: 30px; margin: 32px 0 40px;\"><table style=\"border-collapse: collapse; border: 0; background-color: #F4F4F4; height: 70px; table-layout: fixed; word-wrap: break-word; border-radius: 6px;\"><tbody><tr><td style=\"text-align: center; vertical-align: middle; font-size: 30px;\">";
    msg += code;
    msg += "</td></tr></tbody></table></div>";
    message.setText(msg, "utf-8", "html");
    message.setFrom(new InternetAddress(from, "prac_Admin"));
    return message;
  }

  public String sendSimpleMessage(String to) throws Exception {
    MimeMessage message = createMessage(to);
    System.out.println(from);
    System.out.println(password);
    System.out.println(host);
    System.out.println(port);
    try {
      javaMailSender.send(message);
    } catch (MailException e) {
      e.printStackTrace();
      throw new IllegalArgumentException();
    }
    return code;
  }
}

