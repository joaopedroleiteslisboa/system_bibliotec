package com.system.bibliotec.resource;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.jboss.logging.Param;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.system.bibliotec.event.RecursoCriadorEvent;
import com.system.bibliotec.exception.CpfInvalidoException;
import com.system.bibliotec.exception.CpfInvalidoOuInexistenteException;
import com.system.bibliotec.model.Cliente;
import com.system.bibliotec.model.Endereco;
import com.system.bibliotec.repository.ClienteRepository;
import com.system.bibliotec.repository.LivroRepository;
import com.system.bibliotec.repository.filter.ClienteFilter;
import com.system.bibliotec.service.ClienteService;
import com.system.bibliotec.service.LivroService;
import com.system.bibliotec.service.ultis.CpfUtilsValidator;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ClienteResource {

	
	private final ClienteRepository clienteRepository;

	
	private final ClienteService clienteService;

	
	private final ApplicationEventPublisher publisher;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Page<Cliente> pesquisar(ClienteFilter clienteFilter,Pageable page) {
		return clienteRepository.filtrar(clienteFilter, page);
	}

	@ResponseStatus(code = HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET, params =  "cpf", value = "/find/doc")
	public ResponseEntity<Cliente> findOneByCpfIgnoreCase(@RequestParam(required = true, name = "cpf") String cpf) {
		if(!CpfUtilsValidator.isCPF(cpf)) {throw new CpfInvalidoException("Cpf invalido. Informe outro CPF valido");}
			Optional<Cliente> cliente = clienteRepository.findOneByCpf(cpf);
		return cliente.isPresent() ? ResponseEntity.ok(cliente.get()) : ResponseEntity.notFound().build();
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@RequestMapping(method = RequestMethod.GET, params = "id", value = "/find/")
	public ResponseEntity<Cliente> findById(@RequestParam(required = true) Long id){
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.isPresent() ? ResponseEntity.ok(cliente.get()) : ResponseEntity.notFound().build();
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Cliente> create(@Valid @RequestBody Cliente cliente, HttpServletResponse response) {
		Cliente clienteSalvo = clienteService.criarNovoCliente(cliente);
		publisher.publishEvent(new RecursoCriadorEvent(this, response, clienteSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
	}


	@RequestMapping(method = RequestMethod.PUT, params = "cpf")
	public ResponseEntity<Cliente> update(@RequestParam(required = true) String cpf,
			@Valid @RequestBody Cliente cliente) {
		Cliente clienteAtualizado = clienteService.updateCliente(cpf, cliente);
		return ResponseEntity.ok(clienteAtualizado);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/end" , params = "cpf")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updatePropertyEndereco(@RequestParam(required = true) String cpf, @Valid @RequestBody Endereco endereco) {
		clienteService.updatePropertyEndereco(cpf, endereco);
	}

	@RequestMapping(method = RequestMethod.PUT,  value = "{idCliente}/doc")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updatePropertyCpf(@PathVariable(required = true) Long idCliente, @Valid @RequestBody(required = true) String cpf) {
		clienteService.updatePropertyCpf(idCliente, cpf);
	}

}
