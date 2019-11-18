package com.system.bibliotec.resource;

import com.system.bibliotec.event.RecursoCriadorEvent;
import com.system.bibliotec.model.Categoria;
import com.system.bibliotec.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaRepository repository;

	@Autowired
	private ApplicationEventPublisher publisher;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Page<Categoria> pesquisar(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<Categoria> findById(@PathVariable(required = true) Long id) {
		Optional<Categoria> categoriaSalva = repository.findById(id);
		return categoriaSalva.isPresent() ? ResponseEntity.ok(categoriaSalva.get()) : ResponseEntity.notFound().build();
	}

	@ResponseStatus(code = HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET, params = "/find/nome", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Categoria> findOneByCpfIgnoreCase(@RequestParam(required = true, name = "nome") String nome) {
		if (StringUtils.isEmpty(nome)) {
			throw new IllegalArgumentException("Argumento invalido. Informe um valor valido");
		}
		Optional<Categoria> categoriaSalva = repository.findOneByNome(nome);
		return categoriaSalva.isPresent() ? ResponseEntity.ok(categoriaSalva.get()) : ResponseEntity.notFound().build();
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Categoria> create(@Valid @RequestBody Categoria categoria, HttpServletResponse response) {
		Categoria categoriaSalva = repository.save(categoria);
		publisher.publishEvent(new RecursoCriadorEvent(this, response, categoriaSalva.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Categoria> update(@Valid @RequestBody Categoria categoria) {
		Categoria categoriaSalva = repository.save(categoria);
		return ResponseEntity.ok(categoriaSalva);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
