package com.system.bibliotec.service;

import java.io.IOException;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import com.system.bibliotec.model.VOAcoesContextoEndPoints;
import com.system.bibliotec.repository.AcoesContextoEndPointsRepository;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.audit.AuditEvent;
import org.springframework.boot.actuate.audit.AuditEventRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.system.bibliotec.config.ConstantsUtils;
import com.system.bibliotec.model.PersistentAuditEvent;
import com.system.bibliotec.repository.PersistenceAuditEventRepository;
import com.system.bibliotec.service.ultis.AuditEventConverter;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;

@Repository
public class CustomAuditEventRepositoryService extends HandlerInterceptorAdapter implements AuditEventRepository {

    private static final String AUTHORIZATION_FAILURE = "AUTHORIZATION_FAILURE";

    private final Logger log = LoggerFactory.getLogger(CustomAuditEventRepositoryService.class);

    protected static final int EVENT_DATA_COLUMN_MAX_LENGTH = 1000;

    @Value("#{new Boolean('${bibliotec.custom.audit.event.repository.service.requisicoes}')}")
    private boolean auditarRequisicoes = false; //default false.

    @Value("#{new Boolean('${bibliotec.custom.audit.event.repository.service.http.completo}')}")
    private boolean auditarHttpCompleto = false; //default false.

    @Autowired
    private AcoesContextoEndPointsRepository acoesContextoEndPointsRepository;

    @PersistenceContext
    private EntityManager entityManagerr;

    @Autowired
    private PersistenceAuditEventRepository persistenceAuditEventRepository;

    @Autowired
    private ServicoDoSistema servicoDoSistema;

    @Autowired
    private AuditEventConverter auditEventConverter;


    @Bean
    public EntityManager entityManager() {
        return entityManagerr;
    }

    @Override
    public List<AuditEvent> find(String principal, Instant after, String type) {
        Iterable<PersistentAuditEvent> persistentAuditEvents = persistenceAuditEventRepository
                .findByPrincipalAndAuditEventDateAfterAndAuditEventType(principal, after, type);
        return auditEventConverter.convertToAuditEvent(persistentAuditEvents);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void add(AuditEvent event) {

        if (!AUTHORIZATION_FAILURE.equals(event.getType())
                && !ConstantsUtils.ANONYMOUS_USER.equals(event.getPrincipal())) {

            PersistentAuditEvent persistentAuditEvent = new PersistentAuditEvent();
            persistentAuditEvent.setPrincipal(event.getPrincipal());
            persistentAuditEvent.setAuditEventType(event.getType());
            persistentAuditEvent.setAuditEventDate(event.getTimestamp());
            Map<String, String> eventData = auditEventConverter.convertDataToStrings(event.getData());
            persistentAuditEvent.setData(truncate(eventData));
            persistenceAuditEventRepository.save(persistentAuditEvent);
        }
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        if (auditarRequisicoes) {
            saveAcoesContextoEndPoints(request, response, handler,
                    ServletUriComponentsBuilder.fromCurrentRequest().toUriString(),
                    ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString());
        }
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

        if (auditarRequisicoes) {
            saveAcoesContextoEndPoints(request, response, handler,
                    ServletUriComponentsBuilder.fromCurrentRequest().toUriString(),
                    ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString(), modelAndView);
        }
        super.postHandle(request, response, handler, modelAndView);
    }

    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveAcoesContextoEndPoints(HttpServletRequest request, HttpServletResponse response, Object handler,
                                           String servletCurrentRequest, String serveletURICurrent) throws IOException {

        VOAcoesContextoEndPoints requisicao = new VOAcoesContextoEndPoints();

        requisicao.setBody(IOUtils.toString(request.getInputStream()));
        requisicao.setMethod(request.getMethod().toString());
        if (auditarHttpCompleto) {
            requisicao.setDataHeaders(auditEventConverter.convertDataToStringsHeader(request));
            requisicao.setDataParans(auditEventConverter.convertDataToStringsParam(request));
        }
        requisicao.setRecursoSolicitado(servletCurrentRequest);
        requisicao.setAtividadeEsperada(serveletURICurrent);

        requisicao.setIp(request.getRemoteAddr().toString());

        acoesContextoEndPointsRepository.save(requisicao);
    }

    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveAcoesContextoEndPoints(HttpServletRequest request, HttpServletResponse response, Object handler,
                                           String servletCurrentRequest, String serveletURICurrent, ModelAndView modelAndView) throws IOException {

        VOAcoesContextoEndPoints requisicao = new VOAcoesContextoEndPoints();

        requisicao.setBody(IOUtils.toString(request.getInputStream()));
        requisicao.setMethod(request.getMethod().toString());
        if (auditarHttpCompleto) {
            requisicao.setDataHeaders(auditEventConverter.convertDataToStringsHeader(request));
            requisicao.setDataParans(auditEventConverter.convertDataToStringsParam(request));
        }
        requisicao.setRecursoSolicitado(servletCurrentRequest);
        requisicao.setAtividadeEsperada(serveletURICurrent);

        requisicao.setIp(request.getRemoteAddr().toString());

        acoesContextoEndPointsRepository.save(requisicao);
    }

    private Map<String, String> truncate(Map<String, String> data) {
        Map<String, String> results = new HashMap<>();

        if (data != null) {
            for (Map.Entry<String, String> entry : data.entrySet()) {
                String value = entry.getValue();
                results.put(entry.getKey(), value);
            }
        }
        return results;
    }

}
