package com.system.bibliotec.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.system.bibliotec.event.RecursoCriadorEvent;
import com.system.bibliotec.model.Livro;
import com.system.bibliotec.repository.LivroRepository;
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

	@GetMapping
	public List<Livro> findAll() {

		return livroRepository.findAll();

	}

	@PostMapping
	public ResponseEntity<Livro> create(@Valid @RequestBody Livro livro, HttpServletResponse response) {
		Livro livroSalved = livroService.save(livro);
		 publisher.publishEvent(new RecursoCriadorEvent(this, response,
		 livroSalved.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(livroSalved);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Livro> findById(@PathVariable Long id) {
		Optional<Livro> livro = livroService.findByIdLivro(id);
		return livro.isPresent() ? ResponseEntity.ok(livro.get()) : ResponseEntity.notFound().build();

	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		livroService.deleteLivro(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Livro> update(@PathVariable Long id, @Valid @RequestBody Livro Livro) {
		Livro livroSalved = livroService.updateLivro(id, Livro);
		return ResponseEntity.ok(livroSalved);
	}

	@PutMapping("/{id}/isbn13")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updatePropertyCpfLivro(@PathVariable Long id, @RequestBody String isbn13) {
		livroService.updatePropertyIsbn13Livro(id, isbn13);
	}

	// @GetMapping
	// public Page<Livro> find(@RequestParam(required = false, defaultValue = "%")
	// String nome, Pageable pageable) {
	// return LivroRepository.findByNomeContaining(nome, pageable);
	// }

}
