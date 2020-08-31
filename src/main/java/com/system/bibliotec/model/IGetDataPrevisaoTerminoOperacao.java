package com.system.bibliotec.model;

import java.time.LocalDate;

public interface IGetDataPrevisaoTerminoOperacao {


    default LocalDate getDataPrevisaoTermino(){
        return LocalDate.MIN;
    }

}
