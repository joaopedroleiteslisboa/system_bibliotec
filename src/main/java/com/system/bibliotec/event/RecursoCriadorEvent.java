package com.system.bibliotec.event;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationEvent;


public class RecursoCriadorEvent extends ApplicationEvent {

    /**
     *
     */
    private static final long serialVersionUID = 3955508203542987060L;

    private HttpServletResponse response;
    private Long codigo;
    private String codigoString;

    public RecursoCriadorEvent(Object source, HttpServletResponse response, Long codigo) {
        super(source);
        this.response = response;
        this.codigo = codigo;
    }

    public RecursoCriadorEvent(Object source, HttpServletResponse response, String codigo) {
        super(source);
        this.response = response;
        this.codigoString = codigo;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public Long getCodigo() {
        return codigo;
    }

}
