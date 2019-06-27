package com.system.bibliotec.repository.livro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.system.bibliotec.model.Livro;
import com.system.bibliotec.repository.filter.LivroFilter;

public interface LivroRepositoryQuery {
	
	public Page<Livro> filtrar(LivroFilter livroFilter, Pageable pageable);

}
