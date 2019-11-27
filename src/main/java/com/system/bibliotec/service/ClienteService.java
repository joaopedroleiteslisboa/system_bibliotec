package com.system.bibliotec.service;

import com.system.bibliotec.config.ConstantsUtils;
import com.system.bibliotec.exception.*;
import com.system.bibliotec.model.Cliente;
import com.system.bibliotec.model.Endereco;
import com.system.bibliotec.model.enums.StatusCliente;
import com.system.bibliotec.repository.ClienteRepository;
import com.system.bibliotec.service.operations.OperacaoCliente;
import com.system.bibliotec.service.ultis.CpfUtilsValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

//TODO: Precisa desenvolvedor sobrecarga de metodos para validação ficar mais coerente com um determinado contexto solicitado...
@Service
@Slf4j
public class ClienteService {

	@Autowired
	private OperacaoCliente operacao;

	
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
		

	}	
	
	public boolean isInativo(String cpf) {

		Optional<Cliente> clienteSalvo = findByCpfCliente(cpf);

		if (clienteSalvo.get().isInativo()) {
			return false;
		}
		return true;
	}

	
}
