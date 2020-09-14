package com.system.bibliotec.repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.system.bibliotec.model.Usuario;
import com.system.bibliotec.repository.usuario.UsuarioRepositoryQueryEmail;



@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>, UsuarioRepositoryQueryEmail {

	public List<Usuario> findByPermissoesDescricao(String permissaoDescricao);

	String USERS_BY_LOGIN_CACHE = "usersByEmail";

	String USERS_BY_EMAIL_CACHE = "usersByEmail";

	Optional<Usuario> findOneByChaveAtivacao(String chaveAtivacao);

	List<Usuario> findAllByAtivoIsFalseAndChaveAtivacaoIsNotNullAndCreatedDateBefore(Instant dateTime);

	Optional<Usuario> findOneByChaveRenovacao(String chaveRenovacao);

	Optional<Usuario> findOneByEmailIgnoreCase(String email);

	Optional<Usuario> findOneByUserName(String username);

	Usuario findOneByUserNamee(String username);

	@EntityGraph(attributePaths = "permissao")
	Optional<Usuario> findOneWithPermissoesById(Long id);

	@EntityGraph(attributePaths = "permissao")
	@Cacheable(cacheNames = USERS_BY_EMAIL_CACHE)
	Optional<Usuario> findOneWithPermissaoByEmail(String email);

	@EntityGraph(attributePaths = "permissao")
	@Cacheable(cacheNames = USERS_BY_EMAIL_CACHE)
	Optional<Usuario> findOneWithPermissaoByEmailIgnoreCase(String email);

	Page<Usuario> findAllByEmailNot(Pageable pageable, String email);

}
