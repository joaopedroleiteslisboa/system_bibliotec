package com.system.bibliotec.service.vm;

import com.system.bibliotec.model.Solicitacoes;

import lombok.Data;
import lombok.ToString;

/**
 * SolicitacaoVM
 */
@Data
@ToString
public class SolicitacaoVM {


    private String tipo;


    private String horaSolicitacao;


    private String dataSolicitacao;


    private String dataRetiradaExemplar;


    private String horaRetiradaExemplar;


    private String nomeExeplar;


    private String status;


    private String descricao;


    private boolean rejeitado;


    private String nomeCliente;


    public SolicitacaoVM(Solicitacoes entity) {


        this.tipo = entity.getTipo().toString();
        this.horaSolicitacao = entity.getHoraSolicitacao().toString();
        this.dataSolicitacao = entity.getDataSolicitacao().toString();
        this.dataRetiradaExemplar = entity.getDataRetiradaExemplar().toString();
        this.horaRetiradaExemplar = entity.getHoraRetiradaExemplar().toString();

        //this.nomeExeplar = nomeExemplar;

        this.status = entity.getStatus().toString();
        this.descricao = entity.getDescricao();
        this.rejeitado = entity.isRejeitado();
        this.nomeCliente = entity.getUsuario().getNome();
    }


    public SolicitacaoVM(Solicitacoes entity, String nomeExemplar) {


        this.tipo = entity.getTipo().toString();
        this.horaSolicitacao = entity.getHoraSolicitacao().toString();
        this.dataSolicitacao = entity.getDataSolicitacao().toString();
        this.dataRetiradaExemplar = entity.getDataRetiradaExemplar().toString();
        this.horaRetiradaExemplar = entity.getHoraRetiradaExemplar().toString();
        this.nomeExeplar = nomeExemplar;
        this.status = entity.getStatus().toString();
        this.descricao = entity.getDescricao();
        this.rejeitado = entity.isRejeitado();
        this.nomeCliente = entity.getUsuario().getNome();
    }


}