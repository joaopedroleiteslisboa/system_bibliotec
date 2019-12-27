package com.system.bibliotec.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.system.bibliotec.event.RecursoCriadorEvent;
import com.system.bibliotec.model.Locacao;
import com.system.bibliotec.model.enums.StatusLocacao;
import com.system.bibliotec.repository.LocacaoRepository;
import com.system.bibliotec.service.LocacaoService;

@RestController
@RequestMapping(value = "/locacao")
public class LocacaoResource {

	@Autowired
	private LocacaoRepository locacaoRepository;

	@Autowired
	private LocacaoService locacaoService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public List<Locacao> pesquisar() {

		return locacaoRepository.findAll();

	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Locacao> create(@Valid @RequestBody Locacao locacao, HttpServletResponse response) {
		Locacao locacaoSalva = locacaoService.realizarLocacao(locacao);
		publisher.publishEvent(new RecursoCriadorEvent(this, response, locacaoSalva.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(locacaoSalva);

	}

	@GetMapping("/{id}")
	public ResponseEntity<Locacao> findById(@PathVariable Long id) {
		Locacao locacao = locacaoService.findByIdLocacao(id);
		return ResponseEntity.ok(locacao);

	}

	@PutMapping("/cancelar")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void cancelarLocacao(@PathVariable Long id) {
		locacaoService.cancelarLocacao(id);
	}

	@PutMapping("/{id}/livro")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updatePropertyLivro(@PathVariable Long id, @RequestBody Long idLivro) {
		locacaoService.updatePropertyLivro(id, idLivro);
	}

	@PutMapping("/{id}/cliente")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updatePropertyCliente(@PathVariable Long id, @RequestBody String cpf) {
		locacaoService.updatePropertyCliente(id, cpf);
	}

	@PutMapping("/{id}/devolucao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void devolucaoLivro(@PathVariable Long id) {
		locacaoService.devolucaoLivro(id);
	}

	@PutMapping("/{id}/status")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updatePropertyStatus(@PathVariable Long id, @RequestBody StatusLocacao status) {
		locacaoService.updatePropertyStatusLocacao(id, status);
	}

}
