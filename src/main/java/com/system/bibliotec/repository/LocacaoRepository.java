package com.system.bibliotec.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.system.bibliotec.model.Locacao;



public interface LocacaoRepository extends JpaRepository<Locacao, Long>{

	Optional<Locacao> findById(Long id);

	boolean existsById(Long id);

}
