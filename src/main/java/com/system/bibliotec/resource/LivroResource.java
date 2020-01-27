package com.system.bibliotec.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.system.bibliotec.event.RecursoCriadorEvent;
import com.system.bibliotec.model.Livro;
import com.system.bibliotec.model.Usuario;
import com.system.bibliotec.model.enums.StatusLivro;
import com.system.bibliotec.repository.LivroRepository;
import com.system.bibliotec.repository.UsuarioRepository;
import com.system.bibliotec.repository.dto.projection.ResumoLivro;
import com.system.bibliotec.repository.filter.LivroFilter;
import com.system.bibliotec.security.SecurityUtils;
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
	
	@Autowired
	private UsuarioRepository uRepos;
		
   @PreAuthorize("hasAuthority('ROLE_PESQUISAR_LIVRO') and #oauth2.hasScope('write')")
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Page<Livro> pesquisar(LivroFilter livroFilter, Pageable page) {
	   
	   System.out.println();
	   System.out.println();
	   Usuario s = uRepos.findOneByEmail(SecurityUtils.getCurrentUserLogin().get());
	   
	   System.out.println();
	   
		return livroRepository.filtrar(livroFilter, page);
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, params = "resumo")
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
		Livro livro = livroService.findByIdLivro(id);
		
		return (livro != null) ? ResponseEntity.ok(livro) : ResponseEntity.notFound().build();

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
	public void updatePropertyIsbn13Livro(@PathVariable Long id, @RequestBody String isbn13) {
		livroService.updatePropertyIsbn13Livro(id, isbn13);
	}

	
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}/status")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateStatusLivro(@PathVariable Long id, @RequestBody StatusLivro status) {
		livroService.updateStatusLivro(id, status);
	}
	
	
	
	
	
}
