package com.system.bibliotec.service.ultis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.actuate.audit.AuditEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import com.system.bibliotec.model.PersistentAuditEvent;

@Component
public class AuditEventConverter {

    private static final String Authorization = "Authorization";

    public List<AuditEvent> convertToAuditEvent(Iterable<PersistentAuditEvent> persistentAuditEvents) {
        if (persistentAuditEvents == null) {
            return Collections.emptyList();
        }
        List<AuditEvent> auditEvents = new ArrayList<>();
        for (PersistentAuditEvent persistentAuditEvent : persistentAuditEvents) {
            auditEvents.add(convertToAuditEvent(persistentAuditEvent));
        }
        return auditEvents;
    }

    public AuditEvent convertToAuditEvent(PersistentAuditEvent persistentAuditEvent) {
        if (persistentAuditEvent == null) {
            return null;
        }
        return new AuditEvent(persistentAuditEvent.getAuditEventDate(), persistentAuditEvent.getPrincipal(),
                persistentAuditEvent.getAuditEventType(), convertDataToObjects(persistentAuditEvent.getData()));
    }

    public Map<String, Object> convertDataToObjects(Map<String, String> data) {
        Map<String, Object> results = new HashMap<>();

        if (data != null) {
            for (Map.Entry<String, String> entry : data.entrySet()) {
                results.put(entry.getKey(), entry.getValue());
            }
        }
        return results;
    }

    //declarando statica para ajudar no quesito Thread-Safe...
    public static Map<String, String> convertDataToStringsHeader(HttpServletRequest request) {
        Map<String, String> results = new HashMap<>();
        if (request != null) {
            Enumeration headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String key = (String) headerNames.nextElement();
                String value = (key != null && !key.isEmpty()) ? request.getHeader(key) : "EMPTY";
                results.put(key, value);

            }
        }
        return results;
    }

    //declarando statica para ajudar no quesito Thread-Safe...
    public static Map<String, String> convertDataToStringsParam(HttpServletRequest request) {
        Map<String, String> results = new HashMap<>();
        if (request != null) {
            Enumeration parans = request.getParameterNames();
            while (parans.hasMoreElements()) {
                String key = (String) parans.nextElement();
                String value = (key != null && !key.isEmpty() && request.getParameter(key) != null) ? request.getParameter(key) : "EMPTY";
                results.put(key, value);

            }
        }
        return results;

    }

    public Map<String, String> convertDataToStrings(Map<String, Object> data) {
        Map<String, String> results = new HashMap<>();

        if (data != null) {
            for (Map.Entry<String, Object> entry : data.entrySet()) {

                if (entry.getValue() instanceof WebAuthenticationDetails) {
                    WebAuthenticationDetails authenticationDetails = (WebAuthenticationDetails) entry.getValue();
                    results.put("remoteAddress", authenticationDetails.getRemoteAddress());
                    results.put("sessionId", authenticationDetails.getSessionId());
                } else {
                    results.put(entry.getKey(), Objects.toString(entry.getValue()));
                }
            }
        }
        return results;
    }

}
