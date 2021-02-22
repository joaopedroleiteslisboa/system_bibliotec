package com.system.bibliotec.service.resttemplate;

import java.util.Map;
import java.util.concurrent.Future;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import com.system.bibliotec.model.Endereco;

public interface RestTemplateService {

    @Async
    public <T> Future<T> getAsynchronousResults(String resourceUrl, Class<T> resultType, RestOperations restTemplate);

    public <T> T getResults(String resourceUrl, Class<T> resultType, RestOperations restTemplate);

    public <T> T getClientOnlyResults(String resourceUrl, Class<T> resultType, RestOperations restTemplate);

    public <T> T getForObject(String resourceUrl, Class<T> responseType, RestOperations restTemplate);

    public <T> T getForObject(String resourceUrl, Class<T> responseType, RestOperations restTemplate, Map<String, String> params);

    //restTemplate.exchange
    public <T> T getForObject(String resourceUrl, HttpMethod method, HttpEntity<?> requestEntity, Class<T> responseType, RestOperations restTemplate);

    //restTemplate.exchange
    public <T> T getForObject(String resourceUrl, HttpMethod method, HttpEntity<?> requestEntity, Class<T> responseType, RestOperations restTemplate, Map<String, String> params);

}
