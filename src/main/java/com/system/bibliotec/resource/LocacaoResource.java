package com.system.bibliotec.resource;

import com.system.bibliotec.event.RecursoCriadorEvent;
import com.system.bibliotec.model.Locacao;
import com.system.bibliotec.repository.LocacaoRepository;
import com.system.bibliotec.service.LocacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/locacoes")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LocacaoResource {

	
	private final LocacaoRepository locacaoRepository;
	  
	  
	  private final LocacaoService locacaoService;
	  
	  
	  private final ApplicationEventPublisher publisher;
	  
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
		    Optional<Locacao> locacao = locacaoService.findByIdLocacao(id);
	  return locacao.isPresent() ? ResponseEntity.ok(locacao.get()) : ResponseEntity.notFound().build();
	  
	  }
	  
	  @PutMapping("/cancelar")	  
	  @ResponseStatus(HttpStatus.NO_CONTENT)
	  public void cancelarLocacao(@PathVariable Long id) { locacaoService.cancelarLocacao(id); }
	  
	  
	  
	  
	  @PutMapping("/{id}/livro")	  
	  @ResponseStatus(HttpStatus.NO_CONTENT) 
	  public void updatePropertyLivro(@PathVariable Long id, @RequestBody Long idLivro) {
	  locacaoService.updatePropertyLivro(id, idLivro); }
	  
	  @PutMapping("/{id}/cliente")	  
	  @ResponseStatus(HttpStatus.NO_CONTENT) public void
	  updatePropertyCliente(@PathVariable Long id, @RequestBody Long idCliente) {
	  locacaoService.updatePropertyCliente(id, idCliente); }
	 

}
