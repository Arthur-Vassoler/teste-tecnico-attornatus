package br.com.attornatus.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
      .info(new Info()
        .title("Teste Técnico Attornatus API")
        .description("Esta é a API para o Teste Técnico Attornatus. Ela fornece endpoints para...")
        .version("1.0")
        .contact(new Contact()
          .name("Desenvolvedor Júnior")
          .email("a.h.vasoler@gmail.com"))
        .license(new License()
          .name("Licença de Uso")
          .url("https://www.attornatus.com.br/licenca"))
      );
  }
}
