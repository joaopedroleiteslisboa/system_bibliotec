package com.system.bibliotec.model;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import com.system.bibliotec.model.enums.Status;
import com.system.bibliotec.model.enums.StatusProcessamento;
import com.system.bibliotec.model.enums.TipoSolicitacao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Solicitacoes
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "solicitacoes")
public class Solicitacoes extends AbstractAuditingEntity {


    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TipoSolicitacao tipo;

    @Column(name = "horaSolicitacao")
    private Instant horaSolicitacao = Instant.now();

    @Column(name = "dataSolicitacao")
    private Instant dataSolicitacao = Instant.now();

    @Column(name = "dataRetiradaExemplar")
    private LocalDate dataRetiradaExemplar;

    @Column(name = "horaRetiradaExemplar")
    private LocalTime horaRetiradaExemplar;

    @NotNull
    @Column(name = "idExemplar")
    private Long idExemplar;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "rejeitado")
    private Boolean rejeitado = false;

    @Column(name = "statusProcessamento")
    private StatusProcessamento statusProcessamento;

    @NotNull(message = "Solicitação Precisa ter um usuario vinculado")
    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;
}