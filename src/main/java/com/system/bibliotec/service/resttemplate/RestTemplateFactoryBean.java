package com.system.bibliotec.service.resttemplate;

import java.util.Collections;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("rest_template_factory_bean")
public class RestTemplateFactoryBean {


    private static String OS = System.getProperty("os.name").toLowerCase();


    @Bean(name = "restTemplateDefault")
    public RestTemplate getRestTemplate() {

        RestTemplate restTemplate = null;

        HttpClient httpClient = HttpClients.createDefault();

        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

        requestFactory.setHttpClient(httpClient);

        requestFactory.setConnectionRequestTimeout(10 * 180000); // 30 Minutos
        requestFactory.setConnectTimeout(10 * 180000); // 30 Minutos

        restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(requestFactory));  // BufferingClientHttpRequestFactory Implementação dedicada para não fechar o fluxo de Stream para os logs e demais interceptadores para o Resttemplate
        restTemplate.setInterceptors(Collections.singletonList(new RequestResponseLoggingInterceptorRestTemplate(checarPadraoSistemaOperacional()))); //RequestResponseLoggingInterceptorRestTemplate Logs Request e Response para RestTemplate

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
