package snust.kimth_lab.config;

import org.springframework.context.annotation.Bean;

public class Config {
  @Bean
  public EmbeddedServletContainerCustomizer tomcatCustomizer() {
    return container -> {
      if (container instanceof TomcatEmbeddedServletContainerFactory) {
        TomcatEmbeddedServletContainerFactory tomcat = (TomcatEmbeddedServletContainerFactory) container;
        tomcat.addContextCustomizers(context -> context.setCookieProcessor(new LegacyCookieProcessor()));
      }
    };
  }
}
