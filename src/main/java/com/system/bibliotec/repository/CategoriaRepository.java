package com.system.bibliotec.repository;

import com.system.bibliotec.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Long>{

	@Query(value = "select c from Categoria c where c.nome =:nome")
	public Optional<Categoria> findOneByNome(@Param(value = "nome") String nome);
}
