package com.douglasgabriel.avaliacaodesempenho.utils.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final String PACOTE_BASICO = "com.douglasgabriel.avaliacaodesempenho";
    private static final String TITULO_API = "Sistema de Avaliação de Desempenho";
    private static final String DESCRICAO_API = "Projeto de um Sistema Web, com Finalidade Avaliar o Desempenho de Colaboradores e Gestores.";
    private static final String VERSAO_API = "1.0.0";
    private static final String NOME_CONTATO = "Douglas Gabriel";
    private static final String GITHUB_CONTATO = "https://github.com/douglasbiel95";
    private static final String EMAIL_CONTATO = "douglaasg11@gmail.com";

    @Bean
    public Docket apiDocs(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(PACOTE_BASICO))
                .paths(PathSelectors.any())
                .build().apiInfo(buildApiInfo());
    }

    private ApiInfo buildApiInfo() {
        return new ApiInfoBuilder()
                .title(TITULO_API)
                .description(DESCRICAO_API)
                .version(VERSAO_API)
                .contact(new Contact(NOME_CONTATO, GITHUB_CONTATO, EMAIL_CONTATO))
                .build();
    }
}
