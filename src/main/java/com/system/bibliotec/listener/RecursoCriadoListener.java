package com.system.bibliotec.listener;

import com.system.bibliotec.event.RecursoCriadorEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@Component
public class RecursoCriadoListener implements ApplicationListener<RecursoCriadorEvent> {

	// EVENTO QUE ESCUTA E EXECUTA O EVENTO/CONTEXTO...

	@Override
	public void onApplicationEvent(RecursoCriadorEvent recursoCriadoEvent) {
		HttpServletResponse response = recursoCriadoEvent.getResponse();
		Long codigo = recursoCriadoEvent.getCodigo();

		adicionarHeaderLocation(response, codigo);
	}

	// ESTE METODO ADICIONA NA HEADER HTTP O ID DO NOVO ITEM CRIADO.....

	private void adicionarHeaderLocation(HttpServletResponse response, Long id) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(id).toUri();
		response.setHeader("Location", uri.toASCIIString());
	}

}
