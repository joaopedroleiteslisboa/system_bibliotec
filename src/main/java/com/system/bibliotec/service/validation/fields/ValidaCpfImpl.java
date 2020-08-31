package com.system.bibliotec.service.validation.fields;

import org.springframework.stereotype.Component;

import com.system.bibliotec.exception.CpfInvalidoException;
import com.system.bibliotec.service.ultis.CpfUtilsValidator;

@Component
public class ValidaCpfImpl implements IValidaCpf {

	
	private CpfUtilsValidator utils;
	
	@Override
	public void validaCpf(String cpf) {
		// TODO Auto-generated method stub
		
		if(!utils.isCPF(cpf)) {
			throw new CpfInvalidoException("CPF invalido.");
		}
		
	}

}
