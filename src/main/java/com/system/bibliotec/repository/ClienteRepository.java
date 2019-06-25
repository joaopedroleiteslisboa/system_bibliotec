package com.system.bibliotec.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.system.bibliotec.model.Cliente;


public interface ClienteRepository extends PagingAndSortingRepository<Cliente, Long>{

	public Optional<Cliente> findByCpfStartingWithIgnoreCase(String cpf);

	List<Cliente> findByNomeStartingWithIgnoreCase(String nome);
	
	Optional<Cliente> findByCpf(String cpf);
	
	Optional<Cliente> findById(Long id);	
	
	void deleteByCpf(String cpf);
	
	public Page<Cliente> findByNomeContaining(String nome, Pageable pageable);
}
