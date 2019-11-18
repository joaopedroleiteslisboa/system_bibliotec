package com.system.bibliotec.resource;

import com.system.bibliotec.model.Endereco;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("endereco/findcep")
public class FindCepResource {

	@GetMapping("/{cep}")
	public ResponseEntity<Endereco> getCep(@PathVariable String cep) {

		RestTemplate restTemplate = new RestTemplate();

		String uri = "http://viacep.com.br/ws/{cep}/json/";

		Map<String, String> params = new HashMap<String, String>();
		params.put("cep", cep);

		Endereco endereco = restTemplate.getForObject(uri, Endereco.class, params);

		return new ResponseEntity<Endereco>(endereco, HttpStatus.OK);
	}

}
