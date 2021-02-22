package com.system.bibliotec.event;

import com.system.bibliotec.model.Usuario;
import com.system.bibliotec.security.UserSystem;
import com.system.bibliotec.service.MailService;

import org.springframework.context.ApplicationEvent;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.Serializable;


public class EventoSistema<T> extends ApplicationEvent {

    private T t;

    public Object obj;

    public EventoSistema(Object o, T t) {
        super(o);
        this.obj = o;
        this.t = t;
    }

    public EventoSistema(Object nulll) {
        super(nulll);

    }


    public T getT() {
        return t;
    }


    public boolean isEventSendEmailToUser() {
        return (t instanceof Usuario || t instanceof UserSystem ||
                t instanceof User || t instanceof UserDetails
                && obj instanceof MailService) ? true : false;
    }

    public boolean isNotNullAndMinValue() {
        boolean retorno = false;

        if (tIsNotNull()) {

            if (t instanceof String) {

                retorno = ((!((String) t).isEmpty()) ? true : false);
            } else if (t instanceof Integer) {

                retorno = (((Integer) t) >= Integer.MIN_VALUE) ? true : false;

            } else if (t instanceof Double) {

                retorno = (((Double) t) >= Double.MIN_VALUE) ? true : false;
            } else if (t instanceof Float) {

                retorno = (((Float) t) >= Float.MIN_VALUE) ? true : false;

            } else if (t instanceof Boolean) {

                retorno = true;

            } else if (t instanceof Long) {

                retorno = (((Long) t) >= Long.MIN_VALUE) ? true : false;

            } else if (t instanceof java.util.List) {

                retorno = (((java.util.List) t).isEmpty()) ? true : false;

            } else if (t instanceof Usuario) {

                retorno = true;
            }

        } else {
            retorno = false;
        }

        return retorno;
    }

    private boolean tIsNotNull() {

        return (t != null) ? true : false;
    }


}
