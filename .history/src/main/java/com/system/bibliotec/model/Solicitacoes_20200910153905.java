package com.system.bibliotec.model;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.Table;

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
public class Solicitacoes {

    
    private TipoSolicitacao tipo;

    private Instant dataSolicitacao = Instant.now();

    private long idSolicitante;


    
}