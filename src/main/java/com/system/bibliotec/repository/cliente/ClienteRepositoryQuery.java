package com.system.bibliotec.repository.cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.system.bibliotec.model.Cliente;
import com.system.bibliotec.repository.filter.ClienteFilter;


public interface ClienteRepositoryQuery {
	public Page<Cliente> filtrar(ClienteFilter clienteFilter, Pageable pageable);

}
