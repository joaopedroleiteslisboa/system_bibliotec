package com.system.bibliotec.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Predicate;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.AuthorizationCodeGrantBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static com.google.common.base.Predicates.or;


@Configuration
@EnableSwagger2
public class SpringFoxConfig {

	@Value("${app.client.id}")
    private String clientId;
    @Value("${app.client.secret}")
    private String clientSecret;
    @Value("${info.build.name}")
    private String infoBuildName;

    @Value("${host.full.dns.auth.link}")
	private String authLink;
	
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("API - System Bibliotec")
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(paths())
				.build()
				.apiInfo(apiInfo())
				.useDefaultResponseMessages(false)				
				.globalResponseMessage(RequestMethod.GET,
						Arrays.asList(
								new ResponseMessageBuilder().code(500).message("Detectamos algum problema interno na Aplicação. Informaremos ao nosso suporte para realizar este reparo. Em caso de dúvidas pode entrar em contato atraves do contato (83) 3333-3333.")
										.responseModel(new ModelRef("Error")).build(),
										
								new ResponseMessageBuilder().code(400).message("É necessário analisar os requisitos para acessar este Recurso. Por gentileza tente novamente ou em caso de persistência desse problema ficaremos grato de um FeedBack através do contato (83) 3333-3333.")
								.build(),
								
								new ResponseMessageBuilder().code(200).message("Recurso solicitado atendido com Sucesso")
								.responseModel(new ModelRef("OK")).build()
						)
				).globalResponseMessage(RequestMethod.DELETE,
						
						Arrays.asList(
								new ResponseMessageBuilder().code(500).message("Detectamos algum problema interno na Aplicação. Informaremos ao nosso suporte para realizar este reparo. Em caso de dúvidas pode entrar em contato atraves do contato (83) 3333-3333.")
										.responseModel(new ModelRef("Error")).build(),
										
								new ResponseMessageBuilder().code(400).message("É necessário analisar os requisitos antes de realizar esta operação. Por gentileza tente novamente ou em caso de persistência desse problema ficaremos grato de um FeedBack através do contato (83) 3333-3333.")
								.build(),
								
								new ResponseMessageBuilder().code(200).message("Operação solicitada, atendida com Sucesso.")
								.responseModel(new ModelRef("OK")).build()
						)
						
				).globalResponseMessage(RequestMethod.PUT,
						
						Arrays.asList(
								new ResponseMessageBuilder().code(500).message("Detectamos algum problema interno na Aplicação. Informaremos ao nosso suporte para realizar este reparo. Em caso de dúvidas pode entrar em contato atraves do contato (83) 3333-3333.")
										.responseModel(new ModelRef("Error")).build(),
										
								new ResponseMessageBuilder().code(400).message("É necessário analisar os requisitos antes de realizar esta operação. Por gentileza tente novamente ou em caso de persistência desse problema ficaremos grato de um FeedBack através do contato (83) 3333-3333.")
								.build(),
								
								new ResponseMessageBuilder().code(200).message("Operação solicitada, atendida com Sucesso.")
								.responseModel(new ModelRef("OK")).build()
						)
						
				).globalResponseMessage(RequestMethod.PUT,
						
						Arrays.asList(
								new ResponseMessageBuilder().code(500).message("Detectamos algum problema interno na Aplicação. Informaremos ao nosso suporte para realizar este reparo. Em caso de dúvidas pode entrar em contato atraves do contato (83) 3333-3333.")
										.responseModel(new ModelRef("Error")).build(),
										
								new ResponseMessageBuilder().code(400).message("É necessário analisar os requisitos antes de realizar esta operação. Por gentileza tente novamente ou em caso de persistência desse problema ficaremos grato de um FeedBack através do contato (83) 3333-3333.")
								.build(),
								
								new ResponseMessageBuilder().code(200).message("Operação solicitada, atendida com Sucesso.")
								.responseModel(new ModelRef("OK")).build()
						)
				)
				.securitySchemes(Collections.singletonList(securitySchema()))
		        .securityContexts(Collections.singletonList(securityContext()));

	}


	@Bean
	UiConfiguration uiConfig() {
		return UiConfigurationBuilder.builder()
				.deepLinking(true)
				.displayOperationId(false)
				.defaultModelsExpandDepth(1)
				.defaultModelExpandDepth(1)
				.defaultModelRendering(ModelRendering.EXAMPLE)
				.displayRequestDuration(false)
				.docExpansion(DocExpansion.NONE)
				.filter(false)
				.maxDisplayedTags(null)
				.operationsSorter(OperationsSorter.ALPHA)
				.showExtensions(false)
				.tagsSorter(TagsSorter.ALPHA)
				.supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
				.validatorUrl(null)
				.build();
	}

	
	private OAuth securitySchema() {

        List<AuthorizationScope> authorizationScopeList = newArrayList();
        authorizationScopeList.add(new AuthorizationScope("read", "read all"));
        authorizationScopeList.add(new AuthorizationScope("trust", "trust all"));
        authorizationScopeList.add(new AuthorizationScope("write", "access all"));

        List<GrantType> grantTypes = newArrayList();
        GrantType creGrant = new ResourceOwnerPasswordCredentialsGrant(authLink+"/oauth/token");

        grantTypes.add(creGrant);

        return new OAuth("oauth2schema", authorizationScopeList, grantTypes);

    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.ant("/user/**"))
                .build();
    }

    private List<SecurityReference> defaultAuth() {

        final AuthorizationScope[] authorizationScopes = new AuthorizationScope[3];
        authorizationScopes[0] = new AuthorizationScope("read", "read all");
        authorizationScopes[1] = new AuthorizationScope("trust", "trust all");
        authorizationScopes[2] = new AuthorizationScope("write", "write all");

        return Collections.singletonList(new SecurityReference("oauth2schema", authorizationScopes));
    }

    @Bean
    public SecurityConfiguration securityInfo() {
        return new SecurityConfiguration(clientId, clientSecret, "", "", "", ApiKeyVehicle.HEADER, "", " ");
    }
	private AuthorizationScope[] scopes() {
	    AuthorizationScope[] scopes = { 
	      new AuthorizationScope("read", "Necessario para Acesso e Leitura de Recursos"), 
	      new AuthorizationScope("write", "Necessario para Acesso e Edição ou Criação de Recurso"), 
	       };
	    return scopes;
	}


	private Predicate<String> paths() {
		return or(
				PathSelectors.regex("/reserva.*"),
				PathSelectors.regex("/endereco/findcep.*"),
				PathSelectors.regex("/livros.*"),
				PathSelectors.regex("/locacao.*"),
				PathSelectors.regex("/categorias.*"));
	}
	
	private ApiInfo apiInfo() {
		ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
		apiInfoBuilder.title("System Bibliotec");
		apiInfoBuilder.description("API de Gerenciamento de Reservas e Locações de Livros");
		apiInfoBuilder.version("V1.0.0");
		apiInfoBuilder.contact(new Contact("João Pedro Leite", "https://www.linkedin.com/in/joaopedroleiteslisboa/",
				"joaopedroleite.s.lisboa@outlook.com"));
		apiInfoBuilder.license("GNU GENERAL PUBLIC LICENSE, Version 3");
		apiInfoBuilder.licenseUrl("https://www.gnu.org/licenses/gpl-3.0.en.html");
		apiInfoBuilder.termsOfServiceUrl("https://github.com/joaopedroleiteslisboa/system_bibliotec");
		return apiInfoBuilder.build();
	}
}
