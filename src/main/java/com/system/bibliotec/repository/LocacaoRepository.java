package com.system.bibliotec.repository;

import com.system.bibliotec.model.Locacoes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface LocacaoRepository extends JpaRepository<Locacoes, Long>, GenericRepository<Locacoes, Long>{

	Optional<Locacoes> findById(Long id);

	boolean existsById(Long id);

	@Query(nativeQuery = true)
	int isLivroLocadoAndAtivoToUserContext(String statusLocacao,  Long idLivro, Long idUsuario);

}
