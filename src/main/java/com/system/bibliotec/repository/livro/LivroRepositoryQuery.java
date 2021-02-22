package com.system.bibliotec.repository.livro;

import com.system.bibliotec.model.Livro;
import com.system.bibliotec.repository.dto.projection.ResumoLivro;
import com.system.bibliotec.repository.filter.LivroFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LivroRepositoryQuery {

    Page<Livro> filtrar(LivroFilter livroFilter, Pageable pageable);

    Page<ResumoLivro> resumo(LivroFilter livroFilter, Pageable pageable);

}
