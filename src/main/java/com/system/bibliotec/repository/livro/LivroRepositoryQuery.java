package com.system.bibliotec.repository.livro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.system.bibliotec.model.Livro;
import com.system.bibliotec.repository.filter.LivroFilter;
import com.system.bibliotec.repository.projection.ResumoLivro;

public interface LivroRepositoryQuery {
	
	Page<Livro> filtrar(LivroFilter livroFilter, Pageable pageable);
	Page<ResumoLivro> resumo(LivroFilter livroFilter, Pageable pageable);

}
