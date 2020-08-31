package com.system.bibliotec.service.validation.fields;

import java.lang.reflect.Field;

import org.springframework.stereotype.Component;

import com.system.bibliotec.exception.EnderecoInvalidoException;
import com.system.bibliotec.model.Endereco;

@Component
public class ValidaEndrecoImpl implements IValidaEndereco {

	static final String atributo_desnecessario_1 = "complemento";
	static final String atributo_desnecessario_2 = "ibge";
	static final String MENSAGEM_ERROR_DEFAULT = "Atributo com valor $s em branco";

	@Override
	public void validaEndereco(Endereco endereco) {
		// TODO Auto-generated method stub
		Object valorDoAtributo = null;

		for (Field f : endereco.getClass().getDeclaredFields()) {

			f.setAccessible(true);
			try {
				valorDoAtributo = f.get(endereco);
				if (valorDoAtributo != null) {
					if (!f.getName().equalsIgnoreCase(atributo_desnecessario_1)
							&& !f.getName().equalsIgnoreCase(atributo_desnecessario_2)) {

						if (valorDoAtributo instanceof Long) {

							if ((Long) valorDoAtributo == null) {
								throw new EnderecoInvalidoException(MENSAGEM_ERROR_DEFAULT.concat(f.getName()));
							}
						} else if (valorDoAtributo instanceof String) {
							if ( ((String) valorDoAtributo) == null || ((String) valorDoAtributo).isEmpty()) {
								throw new EnderecoInvalidoException(MENSAGEM_ERROR_DEFAULT.concat(f.getName()));
							}
						}
					}
				}

			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
