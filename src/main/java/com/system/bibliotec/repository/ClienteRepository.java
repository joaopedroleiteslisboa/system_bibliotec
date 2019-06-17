package com.system.bibliotec.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.system.bibliotec.model.Cliente;

@Repository
public interface ClienteRepository extends PagingAndSortingRepository<Cliente, Long>{

	public Optional<Cliente> findByCpfStartingWithIgnoreCase(String cpf);

	List<Cliente> findByNomeStartingWithIgnoreCase(String nome);
	
	Optional<Cliente> findByCpf(String cpf);
	
	Optional<Cliente> findById(Long id);	
	
	void deleteByCpf(String cpf);
	
}
