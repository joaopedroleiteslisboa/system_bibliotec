package com.system.bibliotec.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.system.bibliotec.event.RecursoCriadorEvent;
import com.system.bibliotec.model.Categoria;
import com.system.bibliotec.repository.CategoriaRepository;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaRepository repository;

	
	private final ApplicationEventPublisher publisher;

	@Autowired
	public CategoriaResource(CategoriaRepository repository, ApplicationEventPublisher publisher) {
		this.repository = repository;
		this.publisher = publisher;
	}

	@PreAuthorize("hasAnyRole('ROLE_PESQUISAR_LIVRO','ROLE_ADMIN', 'ROLE_USER_SYSTEM', 'ROLE_USER_ANONIMO') and #oauth2.hasScope('read')")
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Page<Categoria> pesquisar(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@PreAuthorize("hasAnyRole('ROLE_PESQUISAR_LIVRO','ROLE_ADMIN', 'ROLE_USER_SYSTEM', 'ROLE_USER_ANONIMO') and #oauth2.hasScope('read')")
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<Categoria> findById(@PathVariable(required = true) Long id) {
		Optional<Categoria> categoriaSalva = repository.findById(id);
		return categoriaSalva.isPresent() ? ResponseEntity.ok(categoriaSalva.get()) : ResponseEntity.notFound().build();
	}

	@PreAuthorize("hasAnyRole('ROLE_PESQUISAR_LIVRO','ROLE_ADMIN', 'ROLE_USER_SYSTEM', 'ROLE_USER_ANONIMO') and #oauth2.hasScope('read')")
	@ResponseStatus(code = HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET, params = "/find/nome", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Categoria> findOneByNomeIgnoreCase(@RequestParam(required = true, name = "nome") String nome) {
		if (StringUtils.isEmpty(nome)) {
			throw new IllegalArgumentException("Argumento invalido. Informe um valor valido");
		}
		Optional<Categoria> categoriaSalva = repository.findOneByNome(nome);
		return categoriaSalva.isPresent() ? ResponseEntity.ok(categoriaSalva.get()) : ResponseEntity.notFound().build();
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN') and #oauth2.hasScope('write')")
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Categoria> create(@Valid @RequestBody Categoria categoria, HttpServletResponse response) {
		Categoria categoriaSalva = repository.save(categoria);
		publisher.publishEvent(new RecursoCriadorEvent(this, response, categoriaSalva.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
	}

	@PreAuthorize("hasAnyRole('ROLE_CADASTRAR_LIVRO','ROLE_ADMIN', 'ROLE_USER_SYSTEM') and #oauth2.hasScope('write')")
	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Categoria> update(@Valid @RequestBody Categoria categoria) {
		Categoria categoriaSalva = repository.save(categoria);
		return ResponseEntity.ok(categoriaSalva);
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN') and #oauth2.hasScope('write')")
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}


}
