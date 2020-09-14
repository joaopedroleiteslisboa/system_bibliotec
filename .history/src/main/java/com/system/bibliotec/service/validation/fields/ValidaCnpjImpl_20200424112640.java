package com.system.bibliotec.service.validation.fields;

import org.springframework.stereotype.Component;

import com.system.bibliotec.exception.CnpjInvalidoException;
import com.system.bibliotec.exception.CpfInvalidoException;
import com.system.bibliotec.service.ultis.CnpjUtilsValidator;
import com.system.bibliotec.service.ultis.CpfUtilsValidator;

@Component
public class ValidaCnpjImpl implements IvalidaCnpj{

	private CnpjUtilsValidator utils;
	
	@Override
	public void validaCnpj(String cnpj) {
		// TODO Auto-generated method stub
		
		if(!utils.isCNPJ(cnpj)) {
			throw new CnpjInvalidoException("Cnpj Invalido");
		}
	}

}
