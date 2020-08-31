package com.system.bibliotec.repository.pessoa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.system.bibliotec.model.Pessoa;
import com.system.bibliotec.repository.filter.PessoaFilter;

public interface PessoaRepositoryQuery {
	public Page<Pessoa> filtrar(PessoaFilter filter, Pageable pageable);

}
