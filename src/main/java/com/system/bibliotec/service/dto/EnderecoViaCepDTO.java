package com.system.bibliotec.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EnderecoViaCepDTO {


    private String cep;


    private String logradouro;


    private String complemento;


    private String bairro;


    private String localidade;


    private String uf;


    private String ibge;


    private String ddd;


}
