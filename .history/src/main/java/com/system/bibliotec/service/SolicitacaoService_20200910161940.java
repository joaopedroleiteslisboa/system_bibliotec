package com.system.bibliotec.service;



import com.system.bibliotec.exception.RecursoNaoEncontradoException;
import com.system.bibliotec.model.Solicitacoes;
import com.system.bibliotec.repository.SolicitacaoRepository;

import org.springframework.stereotype.Service;

@Service
public class SolicitacaoService {
    

    private final SolicitacaoRepository repository;

    public SolicitacaoService(SolicitacaoRepository repository) {
        this.repository = repository;
    }



    public Solicitacoes findByIdSolicitacao(Long id){

        return repository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Operação Não realizada. Solicitação do Cliente não foi Localizada"));
    }



    public void updateStatus(Long id){

        return repository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Operação Não realizada. Solicitação do Cliente não foi Localizada"));
    }

}