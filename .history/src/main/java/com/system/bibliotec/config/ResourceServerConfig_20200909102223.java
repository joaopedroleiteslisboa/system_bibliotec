package com.system.bibliotec.config;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.zalando.problem.spring.web.advice.security.SecurityProblemSupport;




@Configuration
@EnableResourceServer
@EnableConfigurationProperties(ApiSecurityResourceServerProperties.class)
@org.springframework.context.annotation.Import(SecurityProblemSupport.class)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	private static final String ROOT_PATTERN = "/**";
	private static final String ROOT_PATTERN_AGENDADOR = "/agendador/**";
	private static final String ROOT_PATTERN_REGISTER = "/account/register";
	private static final String ROOT_PATTERN_ACTIVATE = "/account/activate";
	private static final String ROOT_PATTERN_RESET_PASSWORD_FINISH = "/account/reset-password/finish";
	private static final String ROOT_PATTERN_RESET_PASSWORD_INIT = "/account/reset-password/init";
	private static final String ROOT_PATTERN_LIVROS = "/livros";
	private static final String ROOT_PATTERN_OFERTAS = "/ofertas";
	private static final String ROOT_PATTERN_BUSCAR_CEP = "/endereco/findcep/**";
	private static final String ROOT_PATTERN_SWAGGER = "/swagger-ui.html#/**";
	private static final String ROOT_PATTERN_API_DOCS = "/v2/api-docs/**";
	


	private final ApiSecurityResourceServerProperties securityProperties;

	private TokenStore tokenStore;

	private final SecurityProblemSupport problemSupport;



	public ResourceServerConfig(final ApiSecurityResourceServerProperties securityProperties, SecurityProblemSupport problemSupport) {
		this.securityProperties = securityProperties;

		this.problemSupport = problemSupport;
	}


	@Override
	public void configure(final ResourceServerSecurityConfigurer resources) {

		resources.tokenStore(tokenStore());
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.csrf()
				.disable()
				//.addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
				.exceptionHandling()
				.authenticationEntryPoint(problemSupport)
				.accessDeniedHandler(problemSupport)
				.and()
				.authorizeRequests()
					//Todo: verificar a questão de como será definido no swagger a politica de segurança
					
					.antMatchers(HttpMethod.POST, ROOT_PATTERN_AGENDADOR).permitAll()

					.antMatchers(HttpMethod.GET, ROOT_PATTERN_LIVROS).permitAll()

					.antMatchers(HttpMethod.POST, ROOT_PATTERN_RESET_PASSWORD_INIT).anonymous()

					.antMatchers(HttpMethod.POST, ROOT_PATTERN_RESET_PASSWORD_FINISH).anonymous()

					.antMatchers(HttpMethod.POST, ROOT_PATTERN_REGISTER).anonymous()

					.antMatchers(HttpMethod.GET, ROOT_PATTERN_ACTIVATE).anonymous()

					.antMatchers(HttpMethod.GET, ROOT_PATTERN_OFERTAS).anonymous()
					
					.antMatchers(HttpMethod.GET, ROOT_PATTERN_BUSCAR_CEP).anonymous()	
					
					//SWAGGER




					.antMatchers(HttpMethod.GET, ROOT_PATTERN).access("#oauth2.hasScope('read')")
					.antMatchers(HttpMethod.POST, ROOT_PATTERN).access("#oauth2.hasScope('write')")
					.antMatchers(HttpMethod.PATCH, ROOT_PATTERN).access("#oauth2.hasScope('write')")
					.antMatchers(HttpMethod.PUT, ROOT_PATTERN).access("#oauth2.hasScope('write')")
					.antMatchers(HttpMethod.DELETE, ROOT_PATTERN).access("#oauth2.hasScope('write')");



	}



	@Bean
	public DefaultTokenServices tokenServices(final TokenStore tokenStore) {
		DefaultTokenServices tokenServices = new DefaultTokenServices();
		tokenServices.setTokenStore(tokenStore);
		return tokenServices;
	}

	@Bean
	public TokenStore tokenStore() {
		if (tokenStore == null) {
			tokenStore = new JwtTokenStore(jwtAccessTokenConverter());
		}
		return tokenStore;
	}

	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();

		//converter.setSigningKey(getPublicKeyAsString());
		converter.setVerifierKey(getPublicKeyAsString());
		converter.setSigningKey("@admin@");
		
		return converter;
	}



	private String getPublicKeyAsString() {
		try {

			return IOUtils.toString(securityProperties.getJwt().getPublicKey().getInputStream(), UTF_8);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}