package com.system.bibliotec.repository;


import com.system.bibliotec.model.Cliente;
import com.system.bibliotec.repository.cliente.ClienteRepositoryQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends PagingAndSortingRepository<Cliente, Long>, ClienteRepositoryQuery{
	
	
	@Query(value = "select c from Cliente c where c.cpf =:cpf")
	public Optional<Cliente> findOneByCpf(@Param(value = "cpf") String cpf);
	
	List<Cliente> findByNomeStartingWithIgnoreCase(String nome);
		
	Optional<Cliente> findById(Long id);	
	
	void deleteByCpf(String cpf);
	
	public Page<Cliente> findByNomeContaining(String nome, Pageable pageable);
}
