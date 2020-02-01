package com.system.bibliotec.repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.system.bibliotec.model.TipoUsuarioVO;

@Repository
public interface TipoUsuarioVORepository extends JpaRepository<TipoUsuarioVO, Long> { 

	@Query(value = "select * from tipoUsuarioVO where tipoUsuarioVO.tipo = :tipo", nativeQuery = true)
	Optional<TipoUsuarioVO> findOneByTipoIgnoreCase(@Param("tipo")String tipo);

}
