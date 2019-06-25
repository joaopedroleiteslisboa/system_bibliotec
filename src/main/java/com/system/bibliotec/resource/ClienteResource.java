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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.system.bibliotec.event.RecursoCriadorEvent;
import com.system.bibliotec.model.Cliente;
import com.system.bibliotec.model.Endereco;
import com.system.bibliotec.repository.ClienteRepository;
import com.system.bibliotec.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Page<Cliente> pesquisar(Pageable page) {
		return clienteRepository.findAll(page);
	}

	@ResponseStatus(code = HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET, headers = "cpf", value = "/find")
	public ResponseEntity<Cliente> FindCpfCliente(@RequestHeader(required = true, name = "cpf") String cpf) {

		Optional<Cliente> cliente = clienteRepository.findByCpfStartingWithIgnoreCase(cpf);
		return cliente.isPresent() ? ResponseEntity.ok(cliente.get()) : ResponseEntity.notFound().build();
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@RequestMapping(method = RequestMethod.GET, value = "/cod/{id}")
	public ResponseEntity<Cliente> findById(@PathVariable(required = true) Long id){
		
		return ResponseEntity.ok(clienteService.findByIdCliente(id).get());
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Cliente> create(@Valid @RequestBody Cliente cliente, HttpServletResponse response) {
		Cliente clienteSalved = clienteService.criarNovoCliente(cliente);
		publisher.publishEvent(new RecursoCriadorEvent(this, response, clienteSalved.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalved);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(method = RequestMethod.DELETE, headers = "cpf")
	public void delete(@RequestHeader(required = true) String cpf) {
		clienteService.excluirCliente(cpf);
	}

	@RequestMapping(method = RequestMethod.PUT, headers = "cpf")
	public ResponseEntity<Cliente> update(@RequestHeader(required = true) String cpf,
			@Valid @RequestBody Cliente cliente) {
		Cliente clienteAtualizado = clienteService.updateCliente(cpf, cliente);
		return ResponseEntity.ok(clienteAtualizado);
	}

	@RequestMapping(method = RequestMethod.PUT, headers = "cpf", value = "/up/end")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updatePropertyEndereco(@RequestHeader(required = true) String cpf,
			@Valid @RequestBody Endereco endereco) {
		clienteService.updatePropertyEndereco(cpf, endereco);
	}

	@RequestMapping(method = RequestMethod.PUT, headers = "cpf", value = "/up/doc/{idCliente}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updatePropertyCpf(@PathVariable(required = true) Long idCliente,
			@RequestHeader(required = true) String cpf) {
		clienteService.updatePropertyCpf(idCliente, cpf);
	}

}
