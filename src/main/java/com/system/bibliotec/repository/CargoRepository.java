package com.system.bibliotec.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.system.bibliotec.model.Cargo;
@Repository
public interface CargoRepository extends JpaRepository<Cargo, String> { 

	
	@Query(value = "select * from cargos where cargos.nome = :nome", nativeQuery = true)
	Optional<Cargo> findOneByNomeIgnoreCase(@Param("nome")String nome);
	
	@Query(value = "select * from cargos where cargos.codigo = :codigo", nativeQuery = true)
	Optional<Cargo> findOneByCodigoIgnoreCase(@Param("codigo")String codigo);
}
