package com.system.bibliotec.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.system.bibliotec.model.Cliente;
import com.system.bibliotec.repository.cliente.ClienteRepositoryQuery;

@Repository
public interface ClienteRepository extends PagingAndSortingRepository<Cliente, Long>, ClienteRepositoryQuery{

	public Optional<Cliente> findOneByCpf(String cpf);
	
	List<Cliente> findByNomeStartingWithIgnoreCase(String nome);
		
	Optional<Cliente> findById(Long id);	
	
	void deleteByCpf(String cpf);
	
	public Page<Cliente> findByNomeContaining(String nome, Pageable pageable);
}
