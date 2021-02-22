package com.system.bibliotec.service;

import java.lang.reflect.Method;
import java.util.Arrays;

import com.system.bibliotec.model.enums.TipoErrorSistema;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.bibliotec.event.EventoSistema;
import com.system.bibliotec.model.SystemError;
import com.system.bibliotec.model.Usuario;
import com.system.bibliotec.repository.SystemRepository;
import com.system.bibliotec.service.ultis.AuditEventConverter;

import javax.transaction.Transactional;

/**
 * ServicoDoSistema - Dedicada para salvar acões do sistema em banco de dados...
 */
@Slf4j
@Service(value = "servicoDoSistema")
public class ServicoDoSistema {

    @Autowired
    private SystemRepository repository;


    @Transactional
    public void salvarErrorContextoAssync(Throwable ex, Method method, Object[] objects, TipoErrorSistema tipo) {
        // TODO Auto-generated method stub


        log.info(
                "#####################################################################################################################");
        log.info(" REGISTRANDO EM BANCO DE DADOS A OCORRÊNCIA DE EXCEÇÃO DA APLICAÇÃO NA FUNCIONABILIDADE "
                + method.getName().toString());
        log.info(
                "######################################################################################################################");

        SystemError s = SystemError.builder()
                .resolvido(false)
                .tipo(tipo)
                .classs(ex.getClass().getName())
                .descricao((ex != null) ? ExceptionUtils.getRootCauseMessage(ex) : null)
                .operacao(Arrays.toString(objects)).metodoError(method.getName()).build();

        repository.save(s);

        log.info(
                "########################################################################################################################");
        log.info(
                "                                    OCORRÊNCIA DE EXCEÇÃO REGISTRADA COM SUCESSO.                           ");
        log.info(
                "########################################################################################################################");


    }

    @Transactional
    public void salvarErrorContextoGenerico(Throwable ex, String method, TipoErrorSistema tipo, String usuarioContexto,
                                            String operacao) {
        log.info(
                "#####################################################################################################################");
        log.info(" REGISTRANDO EM BANCO DE DADOS A OCORRÊNCIA DE EXCEÇÃO DA APLICAÇÃO NA FUNCIONABILIDADE "
                + method.toString());
        log.info(
                "######################################################################################################################");

        SystemError s = SystemError.builder()
                .resolvido(false)
                .tipo(tipo)
                .classs(ex.getClass().getName())
                .descricao((ex != null) ? ExceptionUtils.getRootCauseMessage(ex) : null)
                .operacao(operacao)
                .metodoError(method)
                .usuarioError(usuarioContexto)
                .build();


        repository.save(s);

        log.info(
                "########################################################################################################################");
        log.info(
                "                                    OCORRÊNCIA DE EXCEÇÃO REGISTRADA COM SUCESSO.                           ");
        log.info(
                "########################################################################################################################");

    }
}