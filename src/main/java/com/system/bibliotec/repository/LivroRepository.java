package com.system.bibliotec.repository;

import com.system.bibliotec.model.Livro;
import com.system.bibliotec.model.enums.StatusLivro;
import com.system.bibliotec.repository.livro.LivroRepositoryQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long>, LivroRepositoryQuery {

	public Optional<Livro> findByIsbn13(String cpf);

	public Page<Livro> findByNomeContaining(String nome, Pageable pageable);

	public Optional<Livro> findById(Long id);

	public void deleteById(Long id);

	public boolean existsById(Long id);

	public Optional<Livro> findOneByCodBarrasIgnoreCase(String codBarras);

	public Optional<Livro> findOneByIsbn13IgnoreCase(String isbn13);

	
	@Query(nativeQuery = true, value = "update livros l set l.statusLivro = :status where l.id = :id")
	public void updateStatusLivro(@Param("status") StatusLivro status, @Param("id") Long id);

}
