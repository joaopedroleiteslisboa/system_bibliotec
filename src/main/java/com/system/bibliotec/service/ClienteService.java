package com.system.bibliotec.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.bibliotec.model.Cliente;
import com.system.bibliotec.model.Endereco;
import com.system.bibliotec.service.operations.IOperacaoCliente;

@Service
public class ClienteService {

	@Autowired
	private IOperacaoCliente operacao;

	public Cliente criarNovoCliente(Cliente cliente) {
		return operacao.save(cliente);

	}

	public Cliente updateCliente(String cpf, Cliente cliente) {

		return operacao.updateCliente(cpf, cliente);
	}

	public void updatePropertyCpf(Long idCliente, String cpf) {

		operacao.updatePropertyCpf(idCliente, cpf);
	}

	public void updatePropertyEndereco(String cpf, Endereco endereco) {
		operacao.updatePropertyEndereco(cpf, endereco);
	}

	public void excluirCliente(String cpf) {

		operacao.deleteCliente(cpf);

	}

	public boolean isInativo(String cpf) {

		return operacao.isInativo(cpf);
	}

	public Cliente findByCpfCliente(String cpf) {

		return operacao.findByCpfCliente(cpf);
	}

}
