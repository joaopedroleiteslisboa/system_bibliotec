package com.system.bibliotec.service.operations;

import com.system.bibliotec.model.Cliente;
import com.system.bibliotec.model.Endereco;
import com.system.bibliotec.model.enums.StatusCliente;

public interface OperacaoCliente {
	
	public Cliente save(Cliente cliente);
	
	public Cliente updateCliente(String cpf, Cliente cliente);
	
	
	
	public void updateStatus(String cpf, StatusCliente updateStatusCliente);
	
	public void updatePropertyCpf(Long id, String cpf);
	
	public void updatePropertyEndereco(String cpf, Endereco endereco);
	
	public void deleteCliente(String cpf);
		
	public Cliente findByIdCliente(Long id);
	
	public Cliente findByCpfCliente(String cpf);
	

}

