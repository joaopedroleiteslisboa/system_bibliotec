package com.system.bibliotec.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.system.bibliotec.service.dto.EnderecoViaCepDTO;
import com.system.bibliotec.service.resttemplate.RestTemplateService;

@DependsOn("rest_template_factory_bean")
@RestController
@RequestMapping("endereco/findcep")
public class FindCepResource {

    private final RestTemplateService restTemplateService;


    private final RestTemplate restTemplateDefault;

    @Autowired
    public FindCepResource(RestTemplateService restTemplateService, @Qualifier("restTemplateDefault") RestTemplate restTemplateDefault) {

        this.restTemplateService = restTemplateService;
        this.restTemplateDefault = restTemplateDefault;
    }

//    @PreAuthorize("permitAll()")
    @GetMapping("/{cep}")
    public ResponseEntity<?> getCep(@PathVariable String cep) {

        ResponseEntity<?> response = null;

        String uri = "http://viacep.com.br/ws/{cep}/json/";

        Map<String, String> params = new HashMap<String, String>();
        params.put("cep", cep);

        EnderecoViaCepDTO endereco = restTemplateService.getForObject(uri, EnderecoViaCepDTO.class, restTemplateDefault, params);

        return new ResponseEntity<EnderecoViaCepDTO>(endereco, HttpStatus.OK);
    }


}

