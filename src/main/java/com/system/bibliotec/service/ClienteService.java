package com.system.bibliotec.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.system.bibliotec.exception.ClienteInadimplenteException;
import com.system.bibliotec.exception.ClienteInativoException;
import com.system.bibliotec.exception.ClienteInexistenteException;
import com.system.bibliotec.exception.CpfInvalidoOuInexistenteException;
import com.system.bibliotec.exception.DocumentoInvalidoException;
import com.system.bibliotec.model.Cliente;
import com.system.bibliotec.model.Endereco;
import com.system.bibliotec.model.enums.StatusCliente;
import com.system.bibliotec.repository.ClienteRepository;
import com.system.bibliotec.service.ultis.CpfUtilsValidator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ClienteService {

	private final ClienteRepository clienteRepository;

	@Transactional
	public Cliente criarNovoCliente(Cliente cliente) {

		
		validaClienteNova(cliente);
		
		log.info("Cadastrando um Novo Cliente: " + cliente.toString());

		return clienteRepository.save(cliente);

	}

	@Transactional
	public Cliente updateCliente(String cpf, Cliente cliente) {

		log.info("Atualizando um Cliente: " + cliente.toString());
		validaClienteExistente(cpf);
		
		Optional<Cliente> clienteSalvo = findByCpfCliente(cpf);


		BeanUtils.copyProperties(cliente, clienteSalvo, "id");
		log.info("Cliente Atualizado: " + cliente.toString());

		return clienteRepository.save(clienteSalvo.get());
	}

	@Transactional
	public Cliente updatePropertyCpf(Long idCliente, String cpf) {

		Optional<Cliente> clienteSalvo = findByIdCliente(idCliente);

		validaClienteExistente(clienteSalvo.get().getCpf());

		log.info("Atualizando Propriedade CPF do Cliente " + clienteSalvo.toString());
		clienteSalvo.get().setCpf(cpf);
		
		log.info("Cliente com CPF Atualizado: " + clienteSalvo.toString());

		return clienteRepository.save(clienteSalvo.get());
	}

	
	@Transactional
	public Cliente updatePropertyEndereco(String cpf, Endereco endereco) {

		validaClienteExistente(cpf);

		Optional<Cliente> clienteSalvo = findByCpfCliente(cpf);

		log.info("Atualizando Propriedade Endereço do Cliente: " + clienteSalvo.toString());
		clienteSalvo.get().setEndereco(endereco);
		
		log.info("Endereço Atualizado");
		return clienteRepository.save(clienteSalvo.get());

	}

	/**
	 * Verifica se um cliente Recem cadastrado  é válido ou não
	 *
	 * @param cliente Cliente para ser analisado
	 * @throws DocumentoInvalidoException Se o Novo cliente for invalido.
	 */
	public void validaClienteNova(Cliente cliente) {

		if (!validaCpf(cliente.getCpf())) {

			throw new DocumentoInvalidoException("Documentação Invalida. Verifique os campos exigentes");
		}
		

	}

	/**
	 * Verifica se um Determinado Cliente existente é Invalido ou Inadimplente
	 *
	 * @param cpf CPF para ser analisado
	 * @throws CpfInvalidoOuInexistenteException Se o CPF não for valido
	 * @throws ClienteInexistenteException Se o Cliente não possuir um cadastro
	 * @throws ClienteInadimplenteException Se Cliente Possuir Pendencias na Empresa
	 */
	public void validaClienteExistente(String cpf) {

		if (!validaCpf(cpf)) {

			throw new CpfInvalidoOuInexistenteException("Cpf do Cliente Invalido. Informe um Cpf Valido.");
		}

		Optional<Cliente> clienteSalvo = findByCpfCliente(cpf);

		if (!clienteSalvo.isPresent()) {

			throw new ClienteInexistenteException("Cliente Inexistente Ou Invalido. Informe um cliente Valido");
		}

		if (clienteSalvo.get().getStatusCliente() == StatusCliente.INADIMPLENTE) {

			throw new ClienteInadimplenteException("Cliente Inadinplente. Operação não realizada");
		}
		
		if (clienteSalvo.get().getStatusCliente() == StatusCliente.INATIVO) {

			throw new ClienteInativoException("Cliente Inativo. Operação não realizada. Informe um administrador para Avaliação");
		}

	}

	/**
	 * Verifica se o CPF é válido ou não
	 *
	 * @param cpf CPF para ser avaliado
	 * @return Verdadeiro se válido
	 */
	private Boolean validaCpf(String cpf) {
		return CpfUtilsValidator.isCPF(cpf);
	}
	
	
	/**
	 * Verifica se um Determinado Cliente é inativo Por meio do  CPF
	 *
	 * @param cpf CPF para ser analisado
	 * @return Verdadeiro se for Inativo
	 */
	public boolean isInativo(String cpf) {
		
		Optional<Cliente> clienteSalvo = findByCpfCliente(cpf);
		
		if(clienteSalvo.get().getStatusCliente() == StatusCliente.INADIMPLENTE) {
			return true;
		}
		return false;
	}

	public Optional<Cliente> findByIdCliente(Long id) {

		log.info("Find Cliente ID: " + id);
		Optional<Cliente> clienteSalva = clienteRepository.findById(id);
		if (!clienteSalva.isPresent()) {
			throw new ClienteInexistenteException(
					"Cliente selecionado invalido ou Inexistente. Informe um Cliente Valido");
		}
		return clienteSalva;
	}

	public Optional<Cliente> findByCpfCliente(String cpf) {

		if (cpf.isEmpty() || !CpfUtilsValidator.isCPF(cpf)) {
			throw new CpfInvalidoOuInexistenteException("Cpf Invalido Ou Inexistente. Informe um Cpf Valido");
		}
		log.info("Find Cliente CPF: " + cpf);
		Optional<Cliente> cliente = clienteRepository.findByCpf(cpf);

		if (!cliente.isPresent()) {
			throw new ClienteInexistenteException("Cliente invalido ou iexistente. Informe outro Cliente.");
		}
		return cliente;
	}
	
	/**
	 * Deletar um Cliente.
	 *
	 * @param cpf CPF para proceder processo de exclusão
	 * @return Deleta  se o determinado Cliente não for Inadimplente ou Inativo.
	 */
	@Transactional
	public void excluirCliente(String cpf) {
		
		validaClienteExistente(cpf);
		
		log.info("Deletando um Cliente, CPF Nº: " + cpf);
		clienteRepository.deleteByCpf(cpf);
		log.info("Cliente: "+cpf+" Deletado");

		
	}

}
