package com.system.bibliotec.resource;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.system.bibliotec.event.RecursoCriadorEvent;
import com.system.bibliotec.model.Livro;
import com.system.bibliotec.repository.LivroRepository;
import com.system.bibliotec.repository.filter.LivroFilter;
import com.system.bibliotec.repository.projection.ResumoLivro;
import com.system.bibliotec.service.LivroService;

@RestController
@RequestMapping("/livros")
public class LivroResource {

	@Autowired
	private LivroRepository livroRepository;

	@Autowired
	private LivroService livroService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Page<Livro> pesquisar(LivroFilter livroFilter, Pageable page) {
		return livroRepository.filtrar(livroFilter, page);
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, params = "summary")
	public Page<ResumoLivro> resumo(LivroFilter livroFilter, Pageable page) {
		return livroRepository.resumo(livroFilter, page);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Livro> create(@Valid @RequestBody Livro livro, HttpServletResponse response) {
		Livro livroSalvo = livroService.save(livro);
		publisher.publishEvent(new RecursoCriadorEvent(this, response, livroSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(livroSalvo);
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<Livro> findById(@PathVariable Long id) {
		Optional<Livro> livro = livroService.findByIdLivro(id);
		return livro.isPresent() ? ResponseEntity.ok(livro.get()) : ResponseEntity.notFound().build();

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		livroService.deleteLivro(id);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<Livro> update(@PathVariable Long id, @Valid @RequestBody Livro Livro) {
		Livro livroSalvo = livroService.updateLivro(id, Livro);
		return ResponseEntity.ok(livroSalvo);
	}

	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}/isbn13")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updatePropertyCpfLivro(@PathVariable Long id, @RequestBody String isbn13) {
		livroService.updatePropertyIsbn13Livro(id, isbn13);
	}

	
}
