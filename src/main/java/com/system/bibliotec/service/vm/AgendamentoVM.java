package com.system.bibliotec.service.vm;

import org.springframework.http.HttpStatus;

public class AgendamentoVM {


    public AgendamentoVM(Object data) {

        this.data = data;
    }

    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


}
