package com.system.bibliotec.service.resttemplate;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import com.system.bibliotec.exception.RequisicaoException;
import com.system.bibliotec.model.enums.TipoErrorSistema;
import com.system.bibliotec.security.SecurityUtils;
import com.system.bibliotec.service.ServicoDoSistema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

@Slf4j
@Service
public class RestTemplateServiceImpl implements RestTemplateService {

    private final ServicoDoSistema errorSistemaService;

    @Autowired
    public RestTemplateServiceImpl(ServicoDoSistema errorSistemaService) {
        this.errorSistemaService = errorSistemaService;
    }

    @Async
    public <T> CompletableFuture<T> getAsynchronousResults(String resourceUrl, Class<T> resultType, RestOperations restTemplate) {
        return CompletableFuture.completedFuture(getForObject(resourceUrl, resultType, restTemplate));
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

        log.info(
                "########################################################################################################################");
        log.info(
                "        REST_TEMPLATE SERVICE         INICIANDO UMA REQUISIÇÃO PARA O SISTEMA " + resourceUrl);
        log.info(
                "########################################################################################################################");
        T t = null;
        int retries = 1;
        boolean done = false;
        Exception excecao = null;
        while (!done && retries <= 10) {
            try {
                t = restTemplate.getForObject(resourceUrl, responseType);
                done = true;
            } catch (Exception e) {

                excecao = e;
                retries++;

                try {
                    Thread.sleep(3000); // aguardar 3 segundos para fazer uma nova tentativa...
                } catch (InterruptedException e1) {

                    e1.printStackTrace();
                }
            }

        }

        if(!done){

            errorSistemaService.salvarErrorContextoGenerico(excecao, Thread.currentThread().getStackTrace()[1].getMethodName().toString(),
                    TipoErrorSistema.REQUISICAO_REST_TEMPLATE, SecurityUtils.getCurrentUserNameId().get(),
                    "REQUISIÇÃO PARA " + resourceUrl);

            throw new RequisicaoException("Operação Não realizada. Não foi possivel realizar a requisição ao "+resourceUrl + " Em decorrencia a uma exceção interna da Aplicação" );
        }

        return t;
    }

    @Override
    public <T> T getForObject(String resourceUrl, Class<T> responseType, RestOperations restTemplate,
                              Map<String, String> params) {

        log.info(
                "########################################################################################################################");
        log.info(
                "        REST_TEMPLATE SERVICE         INICIANDO UMA REQUISIÇÃO PARA O SISTEMA " + resourceUrl);
        log.info(
                "########################################################################################################################");

        T t = null;
        int retries = 1;
        boolean done = false;
        Exception excecao = null;

        while (!done && retries <= 10) {
            try {
                t = restTemplate.getForObject(resourceUrl, responseType, params);
                done = true;
            } catch (Exception e) {
                excecao = e;
                retries++;

                try {
                    Thread.sleep(3000); // aguardar 3 segundos para fazer uma nova tentativa...
                } catch (InterruptedException e1) {

                    e1.printStackTrace();
                }
            }

        }

        if(!done){

            errorSistemaService.salvarErrorContextoGenerico(excecao, Thread.currentThread().getStackTrace()[1].getMethodName().toString(),
                    TipoErrorSistema.REQUISICAO_REST_TEMPLATE, SecurityUtils.getCurrentUserNameId().get(),
                    "REQUISIÇÃO PARA " + resourceUrl);

            throw new RequisicaoException("Operação Não realizada. Não foi possivel realizar a requisição ao "+resourceUrl + " Em decorrencia a uma exceção interna da Aplicação" );
        }

        return t;
    }

    @Override
    public <T> T getForObject(String resourceUrl, HttpMethod method, HttpEntity<?> requestEntity, Class<T> responseType,
                              RestOperations restTemplate) {

        log.info(
                "########################################################################################################################");
        log.info(
                "        REST_TEMPLATE SERVICE         INICIANDO UMA REQUISIÇÃO PARA O SISTEMA " + resourceUrl);
        log.info(
                "########################################################################################################################");

        T t = null;
        int retries = 1;
        boolean done = false;
        Exception excecao = null;
        while (!done && retries <= 10) {
            try {
                t = (T) restTemplate.exchange(resourceUrl, method, requestEntity, responseType);
                done = true;
            } catch (Exception e) {
                excecao = e;
                retries++;

                try {
                    Thread.sleep(3000); // aguardar 3 segundos para fazer uma nova tentativa...
                } catch (InterruptedException e1) {

                    e1.printStackTrace();
                }
            }

        }

        if(!done){

            errorSistemaService.salvarErrorContextoGenerico(excecao, Thread.currentThread().getStackTrace()[1].getMethodName().toString(),
                    TipoErrorSistema.REQUISICAO_REST_TEMPLATE, SecurityUtils.getCurrentUserNameId().get(),
                    "REQUISIÇÃO PARA " + resourceUrl);

            throw new RequisicaoException("Operação Não realizada. Não foi possivel realizar a requisição ao "+resourceUrl + " Em decorrencia a uma exceção interna da Aplicação" );
        }


        return t;
    }

    @Override
    public <T> T getForObject(String resourceUrl,  HttpMethod method, HttpEntity<?> requestEntity, Class<T> responseType, RestOperations restTemplate, Map<String, String> params) {

        log.info(
                "########################################################################################################################");
        log.info(
                "        REST_TEMPLATE SERVICE         INICIANDO UMA REQUISIÇÃO PARA O SISTEMA " + resourceUrl);
        log.info(
                "########################################################################################################################");

        T t = null;
        int retries = 1;
        boolean done = false;
        Exception excecao = null;

        while (!done && retries <= 10) {
            try {
                t = (T) restTemplate.exchange(resourceUrl, method, requestEntity, responseType, params);
                done = true;
            } catch (Exception e) {
                excecao = e;
                retries++;

                try {
                    Thread.sleep(3000); // aguardar 3 segundos para fazer uma nova tentativa...
                } catch (InterruptedException e1) {

                    e1.printStackTrace();
                }
            }

        }

        if(!done){

            errorSistemaService.salvarErrorContextoGenerico(excecao, Thread.currentThread().getStackTrace()[1].getMethodName().toString(),
                    TipoErrorSistema.REQUISICAO_REST_TEMPLATE, SecurityUtils.getCurrentUserNameId().get(),
                    "REQUISIÇÃO PARA " + resourceUrl);

            throw new RequisicaoException("Operação Não realizada. Não foi possivel realizar a requisição ao "+resourceUrl + " Em decorrencia a uma exceção interna da Aplicação" );
        }

        return t;
    }

}
