package com.system.bibliotec.service.operations;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.system.bibliotec.config.ConstantsUtils;
import com.system.bibliotec.exception.ClienteInexistenteException;
import com.system.bibliotec.exception.CpfInvalidoException;
import com.system.bibliotec.model.Cliente;
import com.system.bibliotec.model.Endereco;
import com.system.bibliotec.model.enums.StatusCliente;
import com.system.bibliotec.repository.ClienteRepository;
import com.system.bibliotec.service.ultis.CpfUtilsValidator;
import com.system.bibliotec.service.validation.IValidaCliente;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class OperacaoClienteImpl implements IOperacaoCliente {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private IValidaCliente servicoValidaCliente;

	@Override
	public Cliente save(Cliente cliente) {
		// TODO Auto-generated method stub

		servicoValidaCliente.validaCliente(cliente);
		cliente.setStatusCliente(ConstantsUtils.DEFAULT_VALUE_STATUSCLIENTE);
		cliente.setAtivo(ConstantsUtils.DEFAULT_VALUE_ATIVO);
		log.info("Cadastrando um Novo Cliente: " + cliente.toString());
		return clienteRepository.save(cliente);
	}

	@Override
	@Transactional
	public Cliente updateCliente(String cpf, Cliente cliente) {
		// TODO Auto-generated method stub,
		Cliente clienteSalvo = findByCpfCliente(cpf);

		log.info("Atualizando um Cliente: " + clienteSalvo.toString());

		servicoValidaCliente.validaCliente(clienteSalvo);

		BeanUtils.copyProperties(cliente, clienteSalvo, "id", "cpf");

		servicoValidaCliente.validaCliente(clienteSalvo);

		log.info("Cliente Atualizado: " + clienteSalvo.toString());

		return clienteSalvo;
	}

	@Override
	public void updateStatus(String cpf, StatusCliente updateStatusCliente) {
		// TODO Auto-generated method stub
		Cliente clienteSalvo = findByCpfCliente(cpf);
		log.info("Atualizando Status do Cliente: " + clienteSalvo.toString());

		servicoValidaCliente.validaCliente(clienteSalvo);

		clienteSalvo.setStatusCliente(updateStatusCliente);

		clienteRepository.save(clienteSalvo);
		log.info("Propriedade Status do Cliente: %s  Atualizado " + clienteSalvo.toString());

	}

	@Override
	@Transactional
	public void updatePropertyCpf(Long id, String cpf) {
		// TODO Auto-generated method stub

		Cliente clienteSalvo = findByIdCliente(id);
		servicoValidaCliente.validaCliente(clienteSalvo);

		log.info("Atualizando Propriedade CPF do Cliente " + clienteSalvo.toString());
		clienteSalvo.setCpf(cpf);

		log.info("Cliente %s com CPF Atualizado: " + clienteSalvo.toString());

	}

	@Override
	@Transactional
	public void updatePropertyEndereco(String cpf, Endereco endereco) {
		// TODO Auto-generated method stub

		Cliente clienteSalvo = findByCpfCliente(cpf);
		servicoValidaCliente.validaCliente(clienteSalvo);

		log.info("Atualizando Propriedade Endereço do Cliente: " + clienteSalvo.toString());
		clienteSalvo.setEndereco(endereco);

		log.info("Cliente %s com Endereço Atualizado: " + clienteSalvo.toString());
	}

	@Override
	public void deleteCliente(String cpf) {
		// TODO Auto-generated method stub

		Cliente clienteSalvo = findByCpfCliente(cpf);
		log.info("Deletando  Cliente:" + clienteSalvo.toString());
		servicoValidaCliente.validaCliente(clienteSalvo);

		clienteRepository.deleteByCpf(cpf);
		log.info("Cliente: " + clienteSalvo.toString() + " Deletado");
	}

	@Override
	public Cliente findByIdCliente(Long id) {
		// TODO Auto-generated method stub

		log.info("Find Cliente ID: " + id);
		Optional<Cliente> clienteSalvo = clienteRepository.findById(id);
		if (!clienteSalvo.isPresent()) {
			throw new ClienteInexistenteException(
					"Cliente selecionado invalido ou Inexistente. Informe um Cliente Valido");
		}
		return clienteSalvo.get();
	}

	@Override
	public Cliente findByCpfCliente(String cpf) {
		// TODO Auto-generated method stub
		if (cpf.isEmpty() || !CpfUtilsValidator.isCPF(cpf)) {
			throw new CpfInvalidoException("Cpf Invalido Ou Inexistente. Informe um Cpf Valido");
		}
		log.info("Find Cliente CPF: " + cpf);
		Cliente cliente = clienteRepository.findOneByCpf(cpf);

		if (cliente == null) {
			throw new ClienteInexistenteException("Cliente invalido ou iexistente. Informe outro Cliente.");
		}
		return cliente;
	}

	@Override
	public boolean isInativo(String cpf) {
		// TODO Auto-generated method stub
		Cliente clienteSalvo = findByCpfCliente(cpf);

		if (clienteSalvo.isInativo()) {
			return false;
		}
		return true;
	}
}
