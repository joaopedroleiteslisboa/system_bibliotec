package com.system.bibliotec.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.system.bibliotec.config.ConstantsUtils;
import com.system.bibliotec.exception.ClienteExistenteException;
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

//TODO: Precisa desenvolvedor sobrecarga de metodos para validação ficar mais coerente com um determinado contexto solicitado...
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ClienteService {

	private final ClienteRepository clienteRepository;

	@Transactional
	public Cliente criarNovoCliente(Cliente cliente) {

		validandoNovoCliente(cliente.getCpf());
		cliente.setStatusCliente(ConstantsUtils.DEFAULT_VALUE_STATUSCLIENTE);
		cliente.setAtivo(ConstantsUtils.DEFAULT_VALUE_ATIVO);

		log.info("Cadastrando um Novo Cliente: " + cliente.toString());

		return clienteRepository.save(cliente);

	}

	@Transactional
	public Cliente updateCliente(String cpf, Cliente cliente) {

		Optional<Cliente> clienteSalvo = findByCpfCliente(cpf);

		// Obtendo o corrente Status do Cliente... Para que se tenha prudência com o
		// mesmo...
		cliente.setStatusCliente(clienteSalvo.get().getStatusCliente());

		log.info("Atualizando um Cliente: " + clienteSalvo.toString());

		validandoAtualizacaoClienteExistente(clienteSalvo.get());

		BeanUtils.copyProperties(cliente, clienteSalvo.get(), "id", "cpf");
		log.info("Cliente Atualizado: " + cliente.toString());

		return clienteRepository.save(clienteSalvo.get());
	}

	@Transactional
	public Cliente updatePropertyCpf(Long idCliente, String cpf) {

		validaCpf(cpf);

		Optional<Cliente> clienteSalvo = findByIdCliente(idCliente);

		validandoClienteExistente(clienteSalvo.get());

		log.info("Atualizando Propriedade CPF do Cliente " + clienteSalvo.toString());
		clienteSalvo.get().setCpf(cpf);

		log.info("Cliente com CPF Atualizado: " + clienteSalvo.toString());

		return clienteRepository.save(clienteSalvo.get());
	}

	@Transactional
	public Cliente updatePropertyEndereco(String cpf, Endereco endereco) {
		Optional<Cliente> clienteSalvo = findByCpfCliente(cpf);
		validandoClienteExistente(clienteSalvo.get());

		log.info("Atualizando Propriedade Endereço do Cliente: " + clienteSalvo.toString());
		clienteSalvo.get().setEndereco(endereco);

		log.info("Endereço Atualizado");
		return clienteRepository.save(clienteSalvo.get());

	}

	/**
	 * Deletar um Cliente.
	 *
	 * @param cpf CPF para proceder processo de exclusão
	 * @return Deleta se o determinado Cliente não for Inadimplente ou Inativo.
	 */
	@Transactional
	public void excluirCliente(String cpf) {
		Optional<Cliente> cliente = findByCpfCliente(cpf);
		log.info("Deletando um Cliente, CPF Nº: " + cpf);
		validandoClienteExistente(cliente.get());

		log.info("Deletando um Cliente, CPF Nº: " + cpf);
		clienteRepository.deleteByCpf(cpf);
		log.info("Cliente: " + cpf + " Deletado");

	}

	/**
	 * Verifica se um cliente Recem cadastrado é válido ou não
	 *
	 * @param cliente Cliente para ser analisado
	 * @throws DocumentoInvalidoException Se o Novo cliente for invalido.
	 */
	public void validandoNovoCliente(String cpf) {

		if (!CpfUtilsValidator.isCPF(cpf)) {

			throw new DocumentoInvalidoException("Documentação Invalida. Verifique os campos exigentes");
		}

		Optional<Cliente> cpfVerificado = clienteRepository.findOneByCpf(cpf);

		if (cpfVerificado.isPresent()) {

			throw new ClienteExistenteException(
					"O Cliente informado Já possui registro na base de dados. Informe outro cliente para prosseguir.");
		}

	}

	public void validandoClienteExistente(Cliente cliente) {

		if (!CpfUtilsValidator.isCPF(cliente.getCpf())) {

			throw new CpfInvalidoOuInexistenteException("Cpf do Cliente Invalido. Informe um Cpf Valido.");
		}

		if (cliente.getId() == null) {

			throw new ClienteInexistenteException("Cliente Inexistente Ou Invalido. Informe um cliente Valido");
		}

		if (cliente.getStatusCliente() == StatusCliente.INADIMPLENTE) {

			throw new ClienteInadimplenteException("Cliente Inadinplente. Operação não realizada");
		}

		if (cliente.isInativo()) {

			throw new ClienteInativoException(
					"Cliente Inativo. Operação não realizada. Informe um administrador para Avaliação");
		}

	}

	public void validandoAtualizacaoClienteExistente(Cliente cliente) {

		if (!CpfUtilsValidator.isCPF(cliente.getCpf())) {

			throw new CpfInvalidoOuInexistenteException("Cpf do Cliente Invalido. Informe um Cpf Valido.");
		}

		if (cliente.getId() == null) {

			throw new ClienteInexistenteException("Cliente Inexistente Ou Invalido. Informe um cliente Valido");
		}

		if (cliente.getStatusCliente() == StatusCliente.INADIMPLENTE) {

			throw new ClienteInadimplenteException("Cliente Inadinplente. Operação não realizada");
		}

	}

	private void validaCpf(String cpf) {

		if (!CpfUtilsValidator.isCPF(cpf)) {
			throw new DocumentoInvalidoException("CPF Invalido");
		}
	}

	/**
	 * Verifica se um Determinado Cliente é inativo Por meio do CPF
	 *
	 * @param cpf CPF para ser analisado
	 * @return Verdadeiro se for Inativo
	 */
	public boolean isInativo(String cpf) {

		Optional<Cliente> clienteSalvo = findByCpfCliente(cpf);

		if (clienteSalvo.get().isInativo()) {
			return false;
		}
		return true;
	}

	public Optional<Cliente> findByIdCliente(Long id) {

		log.info("Find Cliente ID: " + id);
		Optional<Cliente> clienteSalvo = clienteRepository.findById(id);
		if (!clienteSalvo.isPresent()) {
			throw new ClienteInexistenteException(
					"Cliente selecionado invalido ou Inexistente. Informe um Cliente Valido");
		}
		return clienteSalvo;
	}

	public Optional<Cliente> findByCpfCliente(String cpf) {

		if (cpf.isEmpty() || !CpfUtilsValidator.isCPF(cpf)) {
			throw new CpfInvalidoOuInexistenteException("Cpf Invalido Ou Inexistente. Informe um Cpf Valido");
		}
		log.info("Find Cliente CPF: " + cpf);
		Optional<Cliente> cliente = clienteRepository.findOneByCpf(cpf);

		if (!cliente.isPresent()) {
			throw new ClienteInexistenteException("Cliente invalido ou iexistente. Informe outro Cliente.");
		}
		return cliente;
	}

}
