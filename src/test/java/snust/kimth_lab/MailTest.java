package snust.kimth_lab;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:/application.yml")
public class MailTest {
  @Value("${spring.mail.username}")
  private static String id;
  @Value("${spring.mail.password}")
  private static String password;
  @Value("${spring.mail.host}")
  private static String host;
  @Value("${spring.mail.port}")
  private static int port;

  public static void main(String[] args) {
    System.out.println(id);
    System.out.println(password);
    System.out.println(host);
    System.out.println(port);
  }
}