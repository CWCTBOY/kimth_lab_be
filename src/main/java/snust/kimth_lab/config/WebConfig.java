package snust.kimth_lab.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
  private static final String origin = "http://localhost:3000";

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry
      .addMapping("/**")
      .allowedOrigins(origin)
      .allowedHeaders("*");
  }
}
