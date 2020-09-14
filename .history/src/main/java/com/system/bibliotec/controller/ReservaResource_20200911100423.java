package com.system.bibliotec.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.system.bibliotec.controller.util.EndPointUtil;
import com.system.bibliotec.event.RecursoCriadorEvent;
import com.system.bibliotec.repository.ReservaRepository;
import com.system.bibliotec.service.ReservaService;
import com.system.bibliotec.service.dto.CancelamentoReservaDTO;
import com.system.bibliotec.service.dto.ReservaDTO;
import com.system.bibliotec.service.vm.ReservaCanceladaVM;
import com.system.bibliotec.service.vm.ReservaVM;

@RestController
@RequestMapping(value = "atendimento/reserva", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ReservaResource {

	@Autowired
	private ReservaRepository reservaRepository;

	@Autowired
	private ReservaService reservaService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@Autowired
	private EndPointUtil endPointUtil;

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER_SYSTEM', 'ROLE_PESQUISAR_RESERVA', 'ROLE_USER_ANONIMO') and #oauth2.hasScope('read')")
	@GetMapping
	public List<ReservaVM> pesquisar(Pageable pageable) {

		return reservaService.findAllByReservaDoUsuario(pageable);

	}

	@PreAuthorize("hasAnyRole('ROLE_CADASTRAR_RESERVA','ROLE_ADMIN', 'ROLE_USER_SYSTEM', 'ROLE_USER_ANONIMO') and #oauth2.hasScope('write')")
	@PostMapping
	public ResponseEntity<ReservaVM> create(@Valid @RequestBody ReservaDTO reservaDTO, HttpServletResponse response) {

		ReservaVM reservaSalved = reservaService.reservaLivro(reservaDTO);

		publisher.publishEvent(new RecursoCriadorEvent(this, response, reservaSalved.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(reservaSalved);

	}

	@PreAuthorize("hasAnyRole('ROLE_CADASTRAR_RESERVA','ROLE_ADMIN', 'ROLE_USER_SYSTEM', 'ROLE_USER_ANONIMO') and #oauth2.hasScope('write')")
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ReservaCanceladaVM cancelarReserva(@Valid @RequestBody CancelamentoReservaDTO dto) {

		return reservaService.cancelarReserva(dto.getIdReserva());
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER_SYSTEM') and #oauth2.hasScope('read')")
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		return endPointUtil.returnObjectOrNotFound(reservaService.findByIdReservaDoUsuario(id));

	}

}
