package com.system.bibliotec.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.system.bibliotec.model.Locacoes;
import com.system.bibliotec.model.enums.Status;
import com.system.bibliotec.repository.LocacaoRepository;
import com.system.bibliotec.service.LocacaoService;
import com.system.bibliotec.service.dto.CancelamentoLocacaoDTO;
import com.system.bibliotec.service.dto.DevolucaoLocacaoDTO;
import com.system.bibliotec.service.dto.LocacaoDTO;
import com.system.bibliotec.service.vm.LocacaoCancelamentoVM;
import com.system.bibliotec.service.vm.LocacaoDevolucaoVM;
import com.system.bibliotec.service.vm.LocacaoVM;

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
	public List<Locacoes> pesquisar() {

		return locacaoRepository.findAll();

	}

	@PreAuthorize("hasAnyRole('ROLE_CADASTRAR_LOCACAO','ROLE_ADMIN', 'ROLE_USER_SYSTEM') and #oauth2.hasScope('write')")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<LocacaoVM> create(@Valid @RequestBody LocacaoDTO locacaoDTO, HttpServletResponse response) {
		LocacaoVM locacaoSalva = locacaoService.realizarLocacao(locacaoDTO);
		publisher.publishEvent(new RecursoCriadorEvent(this, response, locacaoSalva.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(locacaoSalva);

	}

	@PreAuthorize("hasAnyRole('ROLE_PESQUISAR_LOCACAO','ROLE_ADMIN', 'ROLE_USER_SYSTEM') and #oauth2.hasScope('read')")
	@GetMapping("/{id}")
	public ResponseEntity<LocacaoVM> findById(@PathVariable Long id) {
		LocacaoVM locacao = locacaoService.findByIdLocacao(id);
		return ResponseEntity.ok(locacao);

	}

	@PreAuthorize("hasAnyRole('ROLE_CADASTRAR_LOCACAO','ROLE_ADMIN', 'ROLE_USER_SYSTEM', 'ROLE_USER_ANONIMO') and #oauth2.hasScope('read')")
	@DeleteMapping("/cancelar")
	@ResponseStatus(HttpStatus.OK)
	public LocacaoCancelamentoVM cancelarLocacao(CancelamentoLocacaoDTO dto) {
		return locacaoService.cancelarLocacao(dto);
		
	}
	
	@PreAuthorize("hasAnyRole('ROLE_PESQUISAR_LOCACAO','ROLE_ADMIN', 'ROLE_USER_SYSTEM', 'ROLE_USER_ANONIMO') and #oauth2.hasScope('read')")
	@PutMapping("/encerrar")
	@ResponseStatus(HttpStatus.OK)
	public LocacaoDevolucaoVM devolucaoLivro(@RequestBody DevolucaoLocacaoDTO dto) {
		return locacaoService.devolucaoLivro(dto);
	}
	


	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER_SYSTEM') and #oauth2.hasScope('write')")
	@PutMapping("/{id}/status")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updatePropertyStatus(@PathVariable Long id, @RequestBody Status status) {
		locacaoService.updatePropertyStatusLocacao(id, status);
	}

}
