package com.system.bibliotec.service.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.system.bibliotec.config.ConstantsUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public abstract class PessoaDTO {


    @Size(min = 11, max = 11, message = "CPF com formatação Invalida")
    protected String cpf;

    @Size(min = 11, max = 11, message = "CNPJ com formatação Invalida")
    protected String cnpj;


    @NotNull(message = "O seu nome é Obrigatorio")
    @Size(min = 1, max = 50)
    protected String nome;

    @Size(max = 50)
    protected String sobreNome;

    @NotNull(message = "O nome da Rua é Obrigatorio")
    @Size(max = 100)
    protected String rua;

    @NotNull(message = "O contato telefônico é obrigatório")
    @Size(max = 50)
    protected String celular;

    @Size(max = 30)
    protected String telefoneResidencial;

    @NotNull(message = "O campo CEP é Necessario")
    @Size(min = 8, max = 10)
    protected String cep;

    @NotNull(message = "O campo Número da Residência é Necessario")
    @Size(max = 50)
    protected String numero;

    @NotNull(message = "É necessario informar a Data de Nascimento")
    protected String dataNascimento;

    @Size(min = 1, max = 280)
    protected String complemento;

    @NotNull(message = "O Campo Bairro é Necessario")
    @Size(min = 4, max = 60)
    protected String bairro;

    @NotNull(message = "O Campo Cidade é Necessario")
    @Size(min = 3, max = 40)
    protected String cidade;

    @NotNull(message = "O Campo UF é Necessario. Ex: PB, PE RJ")
    @Size(min = 2, max = 2)
    protected String uf;

    protected String ibge;

    @Size(min = 8, max = 9)
    protected String genero;


}
