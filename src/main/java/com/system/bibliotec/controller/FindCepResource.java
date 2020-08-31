package com.system.bibliotec.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.system.bibliotec.model.Endereco;
import com.system.bibliotec.service.resttemplate.RestTemplateService;

@DependsOn("rest_template_factory_bean")
@RestController
@RequestMapping("endereco/findcep")
public class FindCepResource {

	private final RestTemplateService restTemplateService;

	@Qualifier("restTemplateDefault")
	private final RestTemplate restTemplateDefault;

	@Autowired
	public FindCepResource(RestTemplateService restTemplateService, RestTemplate restTemplateDefault) {

		this.restTemplateService = restTemplateService;
		this.restTemplateDefault = restTemplateDefault;
	}

	//@PreAuthorize("permitAll()")
	//@GetMapping("/{cep}")
	public ResponseEntity<?> getCep(@PathVariable String cep) {

		ResponseEntity<?> response = null;
		int retries = 1;
		boolean done = false;

		String uri = "http://viacep.com.br/ws/{cep}/json/";

		Map<String, String> params = new HashMap<String, String>();
		params.put("cep", cep);
		Endereco endereco = null;

		endereco = restTemplateService.getForObject(uri, Endereco.class, restTemplateDefault, params);
		return new ResponseEntity<Endereco>(endereco, HttpStatus.OK);
	}
	
	
	//@PreAuthorize("permitAll()")
	//@GetMapping("/{cep}")
	public ResponseEntity<?> getCep2(@PathVariable String cep) {

		HttpClient httpClient = HttpClients.createDefault();

		
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);

		requestFactory.setConnectionRequestTimeout(1800000);
		requestFactory.setReadTimeout(1800000);
		requestFactory.setConnectTimeout(1800000);
		
		
		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_JSON);

		headers.setCacheControl(CacheControl.noCache());

		HttpEntity<?> entityHttp = new HttpEntity<>(headers);

		
		String uri1 = "http://viacep.com.br/wss/{cep}/json/";
		
		
		RestTemplate restTemplate = new RestTemplate(requestFactory);

		
		
		String uri2 = "http://viacep.com.br/ws/58100432/json/";

		Map<String, String> params = new HashMap<String, String>();
		params.put("cep", cep);

		//String retorno = restTemplate.getForObject(uri1, String.class, params);
		
		ResponseEntity<String> retorno2 = restTemplate.exchange(uri1, HttpMethod.GET, entityHttp, String.class, params);
		
		
		
		
		System.out.println(retorno2);
		
		return new ResponseEntity<Endereco>(HttpStatus.OK);
	}
	
	
	
	@PreAuthorize("permitAll()")
	@GetMapping("/{cep}")
	public ResponseEntity<?> getCep3(@RequestParam String nome, @RequestParam String sobreNome) {

		Corpo c = new Corpo();
		
		
		c.setNome(nome);
		c.setSobreNome(sobreNome);
		
		
		HttpClient httpClient = HttpClients.createDefault();

		
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);

		requestFactory.setConnectionRequestTimeout(1800000);
		requestFactory.setReadTimeout(1800000);
		requestFactory.setConnectTimeout(1800000);
		
		
		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_JSON);

		headers.setCacheControl(CacheControl.noCache());

		HttpEntity<?> entityHttp = new HttpEntity<>(c, headers);

		
		String uri1 = "http://localhost:8082/teste/post";
		
		
		RestTemplate restTemplate = new RestTemplate(requestFactory);

		
		
	
		//String retorno = restTemplate.getForObject(uri1, String.class, params);
		
		ResponseEntity<String> retorno2 = restTemplate.exchange(uri1, HttpMethod.POST, entityHttp, String.class);
		
		
		
		
		System.out.println(retorno2);
		
		return new ResponseEntity<Endereco>(HttpStatus.OK);
	}

}

