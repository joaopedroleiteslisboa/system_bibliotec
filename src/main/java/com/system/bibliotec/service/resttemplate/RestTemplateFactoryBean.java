package com.system.bibliotec.service.resttemplate;

import java.security.UnrecoverableKeyException;
import java.util.Collections;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("rest_template_factory_bean")
public class RestTemplateFactoryBean {

	private RestTemplate restTemplateDefault;

	private static String OS = System.getProperty("os.name").toLowerCase();

	@Bean(name = "restTemplateDefault")
	public RestTemplate getSingletonRestTemplate() {

		if (restTemplateDefault == null) {

			restTemplateDefault = getRestTemplate();
		}
		return restTemplateDefault;
	}

	private RestTemplate getRestTemplate() {
		
		RestTemplate restTemplate = null;

		HttpClient httpClient = HttpClients.createDefault();

		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);

		requestFactory.setConnectionRequestTimeout(10 * 180000); // 30 Minutos
		requestFactory.setReadTimeout(10 * 180000); // 30 Minutos
		requestFactory.setConnectTimeout(10 * 180000); // 30 Minutos

		restTemplate = new RestTemplate(requestFactory);
		//restTemplate.setInterceptors(
				//Collections.singletonList(new RequestResponseLoggingInterceptorRestTemplate(checarPadraoSistemaOperacional())));

		return restTemplate;

	}

	public String checarPadraoSistemaOperacional() {
		String os = null;
		final String diretorio = "system_bibliotec_logs";
		if (isWindows()) {
			os = "C:\\".concat(diretorio);
		} else if (isMac()) {
			os = "/Users/".concat(diretorio);
		} else if (isUnix()) {
			os = "/opt/".concat(diretorio);
		} else if (isSolaris()) {
			os = "/opt/".concat(diretorio);
		} else {
			os = "desc";
		}
		return os;
	}

	private static boolean isWindows() {

		return (OS.indexOf("win") >= 0);

	}

	private static boolean isMac() {

		return (OS.indexOf("mac") >= 0);

	}

	private static boolean isUnix() {

		return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0);

	}

	private static boolean isSolaris() {

		return (OS.indexOf("sunos") >= 0);

	}

}
