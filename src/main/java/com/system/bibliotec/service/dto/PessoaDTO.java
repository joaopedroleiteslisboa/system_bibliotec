package com.system.bibliotec.service.dto;

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
public abstract class PessoaDTO {

	@NotBlank
	@Pattern(regexp = ConstantsUtils.REGEX_EMAIL)
	@Size(min = 1, max = 50)
	protected String nome;

	@NotNull(message = "O nome da Rua é Obrigatorio")
	@Size(max = 100)
	protected String rua;

	@NotNull(message = "O contato telefônico é obrigatório")
	@Size(max = 50)
	protected String celular;

	@Size(max = 30)
	protected String telefoneResidencial;

	@Size(min = 8, max = 10)
	protected String cep;

	@NotBlank(message = "O campo Logradouro é Obrigatório")
	@Size(min = 4, max = 200)
	protected String logradouro;

	@NotBlank(message = "O Campo numero é obrigatorio")
	protected String numero;

	@Size(min = 1, max = 280)
	protected String complemento;

	@NotBlank(message = "O Campo Bairro é Necessario")
	@Size(min = 4, max = 60)
	protected String bairro;

	@NotBlank(message = "O Campo Cidade é Necessario")
	@Size(min = 3, max = 40)
	protected String cidade;

	@NotBlank(message = "O Campo UF é Necessario. Ex: PB, PE RJ")
	@Size(min = 2, max = 2)
	protected String uf;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeRua() {
		return nomeRua;
	}

	public void setNomeRua(String nomeRua) {
		this.nomeRua = nomeRua;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getTelefoneResidencial() {
		return telefoneResidencial;
	}

	public void setTelefoneResidencial(String telefoneResidencial) {
		this.telefoneResidencial = telefoneResidencial;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}
	
}
