package com.system.bibliotec.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.system.bibliotec.model.Permissao;


@Repository
public interface PermissoesRepositorio extends JpaRepository<Permissao, String>{

	@Query(value = "select * from permissao where permissao.descricao = :descricao", nativeQuery = true)
	Optional<Permissao> findOneByDescricaoIgnoreCase(@Param("descricao")String descricao);
}
