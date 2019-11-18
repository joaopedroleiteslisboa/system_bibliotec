package com.system.bibliotec.repository;

import com.system.bibliotec.model.Locacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface LocacaoRepository extends JpaRepository<Locacao, Long>{

	Optional<Locacao> findById(Long id);

	boolean existsById(Long id);

}
