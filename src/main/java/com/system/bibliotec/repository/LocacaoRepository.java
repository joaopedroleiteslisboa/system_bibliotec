package com.system.bibliotec.repository;

import com.system.bibliotec.model.Locacoes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Interface de Repositorio das locações
 *
 * @author João Pedro
 * @see .. sql file > jpa-named-queries.properties
 * @since 10.09.2020
 */
@Repository
public interface LocacaoRepository extends JpaRepository<Locacoes, Long>, GenericRepository<Locacoes, Long>,
        JpaSpecificationExecutor<Locacoes> {

    Optional<Locacoes> findById(Long id);

    boolean existsById(Long id);

    @Query(nativeQuery = true)
    int isLivroLocadoAndAtivoToUserContext(String statusLocacao, Long idLivro, Long idUsuario);

}
