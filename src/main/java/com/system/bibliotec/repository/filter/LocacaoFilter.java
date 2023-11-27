package com.system.bibliotec.repository.filter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.system.bibliotec.model.enums.Status;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@ToString
public class LocacaoFilter {


    public LocacaoFilter(Long idLocacao, Long idExemplar, Status statusLocacao,
                         LocalTime horaLocacaoInicio, LocalTime horaLocacaoFim, LocalTime horaCancelamentoLocacaoInicio,
                         LocalTime horaCancelamentoLocacaoFim, LocalDate dataLocacaoInicio, LocalDate dataLocacaoFim,
                         LocalDate dataCancelamentoLocacaoInicio, LocalDate dataCancelamentoLocacaoFim) {

        this.idLocacao = idLocacao;
        this.idExemplar = idExemplar;
        this.statusLocacao = statusLocacao;
        this.horaLocacaoInicio = horaLocacaoInicio;
        this.horaLocacaoFim = horaLocacaoFim;
        this.horaCancelamentoLocacaoInicio = horaCancelamentoLocacaoInicio;
        this.horaCancelamentoLocacaoFim = horaCancelamentoLocacaoFim;
        this.dataLocacaoInicio = dataLocacaoInicio;
        this.dataLocacaoFim = dataLocacaoFim;
        this.dataCancelamentoLocacaoInicio = dataCancelamentoLocacaoInicio;
        this.dataCancelamentoLocacaoFim = dataCancelamentoLocacaoFim;
    }


    private String createdBy;  // anonymous user online...

    private Long idLocacao;

    private Long idExemplar;

    private Long idUsuario;

    private Status statusLocacao;

    @JsonFormat(pattern = "hh:mm:ss")
    @DateTimeFormat(pattern = "hh:mm:ss")
    private LocalTime horaLocacaoInicio;

    @JsonFormat(pattern = "hh:mm:ss")
    @DateTimeFormat(pattern = "hh:mm:ss")
    private LocalTime horaLocacaoFim;


    @JsonFormat(pattern = "hh:mm:ss")
    @DateTimeFormat(pattern = "hh:mm:ss")
    private LocalTime horaCancelamentoLocacaoInicio;

    @JsonFormat(pattern = "hh:mm:ss")
    @DateTimeFormat(pattern = "hh:mm:ss")
    private LocalTime horaCancelamentoLocacaoFim;


    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataLocacaoInicio;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataLocacaoFim;


    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataCancelamentoLocacaoInicio;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataCancelamentoLocacaoFim;


}
