package com.system.bibliotec.repository;

import com.system.bibliotec.model.Solicitacoes;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface SolicitacaoRepository extends PagingAndSortingRepository<Solicitacoes, Long>, GenericRepository<Solicitacoes, Long> {
    


}