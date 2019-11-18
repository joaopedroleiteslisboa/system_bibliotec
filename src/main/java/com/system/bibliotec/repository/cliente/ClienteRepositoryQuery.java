package com.system.bibliotec.repository.cliente;

import com.system.bibliotec.model.Cliente;
import com.system.bibliotec.repository.filter.ClienteFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ClienteRepositoryQuery {
	public Page<Cliente> filtrar(ClienteFilter clienteFilter, Pageable pageable);

}
