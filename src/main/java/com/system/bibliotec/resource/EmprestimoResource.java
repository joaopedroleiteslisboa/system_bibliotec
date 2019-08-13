package com.system.bibliotec.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
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
import com.system.bibliotec.repository.LocacaoRepository;
import com.system.bibliotec.service.LocacaoService;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoResource {

	
	  @Autowired private LocacaoRepository locacaoRepository;
	  
	  @Autowired
	  private LocacaoService emprestimoService;
	  
	  @Autowired
	  private ApplicationEventPublisher publisher;
	  
	  @GetMapping
	  public List<Locacao> pesquisar() {
	  
	  return locacaoRepository.findAll();
	  
	  }
	  
	  @PostMapping
	  public ResponseEntity<Locacao> create(@Valid @RequestBody Locacao locacao, HttpServletResponse response) {
		  Locacao emprestimoSalvo = emprestimoService.realizarLocacao(locacao);
		  publisher.publishEvent(new RecursoCriadorEvent(this, response, emprestimoSalvo.getId())); 
		  return ResponseEntity.status(HttpStatus.CREATED).body(emprestimoSalvo); 
		  
	  }
	  
	  @GetMapping("/{id}")
	  public ResponseEntity<Locacao> findById(@PathVariable Long id) {
		    Optional<Locacao> locacao = emprestimoService.findByIdLocacao(id);
	  return locacao.isPresent() ? ResponseEntity.ok(locacao.get()) : ResponseEntity.notFound().build();
	  
	  }
	  
	  @PutMapping("/cancelar")	  
	  @ResponseStatus(HttpStatus.NO_CONTENT)
	  public void cancelarLocacao(@PathVariable Long id) { emprestimoService.cancelarEmprestimo(id); }
	  
	  
	  @PutMapping("/{id}/idLivro")	  
	  @ResponseStatus(HttpStatus.NO_CONTENT) 
	  public void updatePropertyLivro(@PathVariable Long id, @RequestBody Long idLivro) {
	  emprestimoService.updatePropertyLivro(id, idLivro); }
	  
	  @PutMapping("/{id}/idCliente")
	  
	  @ResponseStatus(HttpStatus.NO_CONTENT) public void
	  updatePropertyCliente(@PathVariable Long id, @RequestBody Long idCliente) {
	  emprestimoService.updatePropertyCliente(id, idCliente); }
	 

}
