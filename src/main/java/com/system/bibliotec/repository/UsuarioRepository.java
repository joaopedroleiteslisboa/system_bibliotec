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

	String USERS_BY_LOGIN_CACHE = "usersByLogin";

	String USERS_BY_EMAIL_CACHE = "usersByEmail";

	Optional<Usuario> findOneByChaveAtivacao(String chaveAtivacao);

	List<Usuario> findAllByActivatedIsFalseAndActivationKeyIsNotNullAndCreatedDateBefore(Instant dateTime);

	Optional<Usuario> findOneByChaveRenovacao(String resetKey);

	Optional<Usuario> findOneByEmailIgnoreCase(String email);

	Optional<Usuario> findOneByLogin(String login);

	@EntityGraph(attributePaths = "permissao")
	Optional<Usuario> findOneWithPermissoesById(Long id);

	@EntityGraph(attributePaths = "permissao")
	@Cacheable(cacheNames = USERS_BY_LOGIN_CACHE)
	Optional<Usuario> findOneWithPermissoesByLogin(String login);

	@EntityGraph(attributePaths = "permissao")
	@Cacheable(cacheNames = USERS_BY_EMAIL_CACHE)
	Optional<Usuario> findOneWithPermissoesByEmailIgnoreCase(String email);

	Page<Usuario> findAllByLoginNot(Pageable pageable, String login);

}
