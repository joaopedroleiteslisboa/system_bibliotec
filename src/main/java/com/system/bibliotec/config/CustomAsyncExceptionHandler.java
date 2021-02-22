package com.system.bibliotec.config;

import java.lang.reflect.Method;
import java.util.Arrays;

import com.system.bibliotec.model.enums.TipoErrorSistema;
import com.system.bibliotec.service.operacoes.auditor.IAuditorTokenDeUsuarioDoContexto;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.system.bibliotec.service.ServicoDoSistema;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

    @Autowired
    private ApplicationContext applicationContext;

    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        // TODO Auto-generated method stub

        this.applicationContext = applicationContext;
    }

    @Override
    public void handleUncaughtException(final Throwable ex, final Method method, final Object... objects) {

        log.error(
                "###########################################################################################################################################");

        log.error(
                " EXCEÇÃO NO CONTEXTO ASSINCRONO DA APLICAÇÃO CAPTURADA. INICIANDO PROCESSO DE PERSISTÊNCIA EM BANCO DE DADOS PARA ANALISE FUTURA DA FUNCIONABILIDADE "
                        + method.getName().toString(),
                ex);
        log.error(
                "############################################################################################################################################");

        ServicoDoSistema servicoErrors = this.applicationContext.getBean("servicoDoSistema",
                ServicoDoSistema.class);

        servicoErrors.salvarErrorContextoAssync(ex, method, objects, TipoErrorSistema.PROCESSAMENTO_ASSINCRONO);

    }

}