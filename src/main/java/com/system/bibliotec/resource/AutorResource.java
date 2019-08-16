package com.system.bibliotec.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.system.bibliotec.repository.ClienteRepository;
import com.system.bibliotec.repository.LivroRepository;
import com.system.bibliotec.service.LivroService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("autores/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AutorResource {

	
	private final LivroRepository livroRepository;

	
	private final LivroService livroService;

	
	private ApplicationEventPublisher publisher;
	
	
	
}
