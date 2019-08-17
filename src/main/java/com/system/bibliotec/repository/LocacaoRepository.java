package com.system.bibliotec.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.system.bibliotec.model.Locacao;


@Repository
public interface LocacaoRepository extends JpaRepository<Locacao, Long>{

	Optional<Locacao> findById(Long id);

	boolean existsById(Long id);

}
