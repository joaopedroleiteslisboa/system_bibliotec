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
import com.system.bibliotec.model.Cliente;
import com.system.bibliotec.model.Reserva;
import com.system.bibliotec.repository.ReservaRepository;
import com.system.bibliotec.service.ReservaService;

@RestController
@RequestMapping(value = "/reservas", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ReservaResource {

	@Autowired
	private ReservaRepository reservaRepository;

	@Autowired
	private ReservaService reservaService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public Page<Reserva> pesquisar(Pageable pageable) {

		return reservaRepository.findAll(pageable);

	}

	@PostMapping
	public ResponseEntity<Reserva> create(@Valid @RequestBody Reserva reserva, HttpServletResponse response) {
		Reserva reservaSalved = reservaService.save(reserva);
		publisher.publishEvent(new RecursoCriadorEvent(this, response, reservaSalved.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(reservaSalved);

	}

	@GetMapping("/{id}")
	public ResponseEntity<Reserva> findById(@PathVariable Long id) {
		Reserva reserva = reservaService.findByIdReserva(id);
		return (reserva != null) ? ResponseEntity.ok(reserva) : ResponseEntity.notFound().build();

	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		reservaService.deleteReserva(id);
	}

	@PutMapping("/{id}/cliente")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updatePropertyCliente(@PathVariable Long id, @RequestBody Cliente cliente) {

		reservaService.updatePropertyCliente(id, cliente);
	}

}
