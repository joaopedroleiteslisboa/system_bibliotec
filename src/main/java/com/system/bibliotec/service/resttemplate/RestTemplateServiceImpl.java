package com.system.bibliotec.service.resttemplate;

import java.util.Map;
import java.util.concurrent.Future;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

@Service
public class RestTemplateServiceImpl implements RestTemplateService {

    @Async
    public <T> Future<T> getAsynchronousResults(String resourceUrl, Class<T> resultType, RestOperations restTemplate) {
        return new AsyncResult<T>(getForObject(resourceUrl, resultType, restTemplate));
    }

    @Override
    public <T> T getClientOnlyResults(String resourceUrl, Class<T> resultType, RestOperations clientOnlyrestTemplate) {
        return getForObject(resourceUrl, resultType, clientOnlyrestTemplate);
    }

    @Override
    public <T> T getResults(String resourceUrl, Class<T> resultType, RestOperations restTemplate) {
        return getForObject(resourceUrl, resultType, restTemplate);
    }

    @Override
    public <T> T getForObject(String resourceUrl, Class<T> responseType, RestOperations restTemplate) {
        T t = null;
        int retries = 1;
        boolean done = false;
        while (!done && retries <= 10) {
            try {
                t = restTemplate.getForObject(resourceUrl, responseType);
                done = true;
            } catch (Exception e) {
                retries++;

                try {
                    Thread.sleep(3000); // aguardar 3 segundos para fazer uma nova tentativa...
                } catch (InterruptedException e1) {

                    e1.printStackTrace();
                }
            }

        }
        return t;
    }

    @Override
    public <T> T getForObject(String resourceUrl, Class<T> responseType, RestOperations restTemplate,
                              Map<String, String> params) {
        T t = null;
        int retries = 1;
        boolean done = false;
        while (!done && retries <= 10) {
            try {
                t = restTemplate.getForObject(resourceUrl, responseType, params);
                done = true;
            } catch (Exception e) {
                retries++;

                try {
                    Thread.sleep(3000); // aguardar 3 segundos para fazer uma nova tentativa...
                } catch (InterruptedException e1) {

                    e1.printStackTrace();
                }
            }

        }
        return t;
    }

    @Override
    public <T> T getForObject(String resourceUrl, HttpMethod method, HttpEntity<?> requestEntity, Class<T> responseType,
                              RestOperations restTemplate) {
        T t = null;
        int retries = 1;
        boolean done = false;
        while (!done && retries <= 10) {
            try {
                t = (T) restTemplate.exchange(resourceUrl, method, requestEntity, responseType);
                done = true;
            } catch (Exception e) {
                retries++;

                try {
                    Thread.sleep(3000); // aguardar 3 segundos para fazer uma nova tentativa...
                } catch (InterruptedException e1) {

                    e1.printStackTrace();
                }
            }

        }
        return t;
    }

    @Override
    public <T> T getForObject(String resourceUrl, HttpMethod method, HttpEntity<?> requestEntity, Class<T> responseType, RestOperations restTemplate, Map<String, String> params) {
        T t = null;
        int retries = 1;
        boolean done = false;
        while (!done && retries <= 10) {
            try {
                t = (T) restTemplate.exchange(resourceUrl, method, requestEntity, responseType, params);
                done = true;
            } catch (Exception e) {
                retries++;

                try {
                    Thread.sleep(3000); // aguardar 3 segundos para fazer uma nova tentativa...
                } catch (InterruptedException e1) {

                    e1.printStackTrace();
                }
            }

        }
        return t;
    }


}
