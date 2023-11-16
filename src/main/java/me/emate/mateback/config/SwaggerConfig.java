package me.emate.mateback.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Swagger를 설정하기 위한 config입니다.
 */
@Component
public class SwaggerConfig {
  private static final String TITLE = "emate.me REST API 명세서";
  private static final String VERSION = "1.0";
  private static final String DESCRIPTION = "emate back-end 서버 rest api 명세서입니다.";

  /**
   * Open api를 설정합니다.
   *
   * @return 설정한 openApi
   */
  @Bean
  public OpenAPI openAPI() {
    Info info = new Info().title(TITLE).version(VERSION)
        .description(DESCRIPTION)
        .contact(new Contact().name("메이트")
            .email("uasaha000@naver.com").url("https://emate.me"));

    return new OpenAPI()
        .components(new Components())
        .info(info);
  }
}
