package snust.kimth_lab.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

  @Bean
  public Docket api() {
    Docket docket = new Docket(DocumentationType.OAS_30)
      .useDefaultResponseMessages(false)
      .select()
      .apis(RequestHandlerSelectors.basePackage("snust.kimth_lab.controller"))
      .paths(PathSelectors.any())
      .build()
      .apiInfo(apiInfo());
    return docket;
  }

  private ApiInfo apiInfo() {
    ApiInfo builder = new ApiInfoBuilder()
      .title("kimth_lab API Docs")
      .description("API Docs for kimth_lab")
      .version("1.0.0")
      .build();
    return builder;
  }
}
