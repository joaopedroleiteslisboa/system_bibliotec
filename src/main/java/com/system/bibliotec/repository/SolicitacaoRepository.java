package com.system.bibliotec.repository;

import java.util.List;
import java.util.Optional;

import com.system.bibliotec.model.Solicitacoes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Interface de Repositorio das solicitações
 *
 * @author João Pedro
 * @see .. sql file > jpa-named-queries.properties
 * @since 10.09.2020
 */
@Repository
public interface SolicitacaoRepository extends JpaRepository<Solicitacoes, Long>, JpaSpecificationExecutor<Solicitacoes> {


    @Query(nativeQuery = true)
    Optional<List<Solicitacoes>> findAllSolicitacoesByUserContextAndStatus(String status);

    @Query(nativeQuery = true)
    Optional<List<Solicitacoes>> findAllSolicitacoesByUserContextAndStatus(String status, Long idUsuario);

}