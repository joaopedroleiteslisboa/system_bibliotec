package com.system.bibliotec.repository.filter;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.system.bibliotec.model.enums.Status;


@Getter
@Setter
@ToString
public class ReservaFilter {


    public ReservaFilter(Long idReserva, Long idExemplar, Status status,
                         LocalTime horaReservaInicio, LocalTime horaReservaFim, LocalDate dataReservaInicio,
                         LocalDate dataReservaFim, LocalDate dataPrevisaoTermino) {

        this.idReserva = idReserva;
        this.idExemplar = idExemplar;
        this.status = status;
        this.horaReservaInicio = horaReservaInicio;
        this.horaReservaFim = horaReservaFim;
        this.dataReservaInicio = dataReservaInicio;
        this.dataReservaFim = dataReservaFim;
        this.dataPrevisaoTermino = dataPrevisaoTermino;
    }


    private Long idReserva;

    private Long idUsuario;

    private Long idExemplar;

    private String createdBy;  // anonymous user online...

    private Status status;

    @JsonFormat(pattern = "kk:mm:ss")
    private LocalTime horaReservaInicio;


    @JsonFormat(pattern = "kk:mm:ss")
    private LocalTime horaReservaFim;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate dataReservaInicio;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate dataReservaFim;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate dataPrevisaoTermino;


}
