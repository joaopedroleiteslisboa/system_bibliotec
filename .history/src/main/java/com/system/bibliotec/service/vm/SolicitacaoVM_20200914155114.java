package com.system.bibliotec.service.vm;

import com.system.bibliotec.model.Solicitacoes;

/**
 * SolicitacaoVM
 */
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
        this.dataSolicitacao = dataSolicitacao;
        this.dataRetiradaExemplar = dataRetiradaExemplar;
        this.horaRetiradaExemplar = horaRetiradaExemplar;
        this.nomeExeplar = nomeExeplar;
        this.status = status;
        this.descricao = descricao;
        this.rejeitado = rejeitado;
        this.nomeCliente = nomeCliente;
    }
    

}