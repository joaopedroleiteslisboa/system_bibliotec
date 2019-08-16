package com.system.bibliotec.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.system.bibliotec.model.Livro;
import com.system.bibliotec.repository.livro.LivroRepositoryQuery;


@Repository
public interface LivroRepository extends JpaRepository<Livro, Long>, LivroRepositoryQuery{

	public Optional<Livro> findByIsbn13(String cpf);
	
	public Page<Livro> findByNomeContaining(String nome, Pageable pageable);

	public Optional<Livro> findById(Long id);

	public void deleteById(Long id);

	public boolean existsById(Long id);

	public Optional<Livro> findOneByCodBarrasIgnoreCase(String codBarras);

	public Optional<Livro> findOneByIsbn13IgnoreCase(String isbn13);

}
