package com.system.bibliotec.service.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.system.bibliotec.model.Cliente;
import com.system.bibliotec.service.validation.fields.IValidaCpf;
import com.system.bibliotec.service.validation.fields.IValidaEndereco;

@Component
public class ValidaClienteImpl implements IValidaCliente {

	@Autowired
	private IValidaCpf servicoValidaCpf;
	
	@Autowired
	private IValidaEndereco servicoValidaEndereco;
	
	
	
	@Override
	public void validaCliente(Cliente cliente) {
		// TODO Auto-generated method stub
				
		servicoValidaCpf.validaCpf(cliente.getCpf());
		
		servicoValidaEndereco.validaEndereco(cliente.getEndereco());
		
	}

}
