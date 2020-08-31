package com.system.bibliotec.service;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.bibliotec.event.EventoSistema;
import com.system.bibliotec.model.SystemError;
import com.system.bibliotec.model.Usuario;
import com.system.bibliotec.repository.SystemRepository;
import com.system.bibliotec.service.ultis.AuditEventConverter;

/**
 * ServicoDoSistema - Dedicada para salvar acões do sistema em banco de dados...
 */
@Service(value = "servicoDoSistema")
public class ServicoDoSistema {

	@Autowired
	private SystemRepository repository;

	
	

	public void saveUserContextErrorLoginAndId(Throwable ex, String methodName, String user, String idEntity) {

		SystemError s = new SystemError.Builder().withDone(false).withMetodoError(methodName.toString())
				.withClasss(ex.getClass().toString()).withUsuarioError(user).withIdUser(idEntity)
				.withDescricao(ex.getMessage()).build();

		repository.save(s);
	}

	public void saveContextErrorAssyncException(Throwable ex, Method method, String currentAuditorUser,
			Object[] objects) {

		SystemError s = new SystemError.Builder().withDone(false).withMetodoError(method.getName())
				.withClasss(ex.getClass().toString()).withUsuarioError(currentAuditorUser)
				.withDescricao(Arrays.toString(objects)).build();

		repository.save(s);
	}

	public void saveContextErrorDefault(Throwable ex, Method method, Object[] objects) {

		SystemError s = new SystemError.Builder().withDone(false).withMetodoError(method.getName())
				.withClasss(ex.getClass().toString()).withDescricao(Arrays.toString(objects)).build();

		repository.save(s);
	}
		
	public void saveContextErrorDefault(Throwable ex, String methodName, String currentAuditorUser, String operacao) {

		SystemError s = new SystemError.Builder().withDone(false).withMetodoError(methodName)
				.withClasss(ex.getClass().toString()).withDescricao(ExceptionUtils.getRootCauseMessage(ex))
				.withUsuarioError(currentAuditorUser)
				.withOperacao(operacao).build();

		repository.save(s);
	}

}