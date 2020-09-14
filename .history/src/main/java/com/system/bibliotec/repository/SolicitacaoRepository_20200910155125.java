package com.system.bibliotec.repository;

import java.util.List;
import java.util.Optional;

import com.system.bibliotec.model.Solicitacoes;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface SolicitacaoRepository extends PagingAndSortingRepository<Solicitacoes, Long>,
                                                     GenericRepository<Solicitacoes, Long> {
    


                                                        @Query(nativeQuery = true)
	Optional<List<Solicitacoes> isLivroLocadoAndAtivoToUserContext(String statusLocacao,  Long idLivro, Long idUsuario);

}