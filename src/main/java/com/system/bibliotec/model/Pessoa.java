package com.system.bibliotec.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.system.bibliotec.model.embeddeds.Contato;
import com.system.bibliotec.model.enums.Genero;
import com.system.bibliotec.model.enums.StatusPessoa;
import com.system.bibliotec.model.enums.TipoPessoa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class Pessoa extends AbstractAuditingEntity {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 5293427550465463236L;

	@NotBlank(message = "O nome é Obrigatorio")
	@Size(min = 3, max = 60)
	@Column(name = "nome")
	private String nome;

	@Size(min = 3, max = 50)
	@Column(name = "sobreNome")
	private String sobreNome;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "genero")
	private Genero genero;
	
	@NotBlank
	@Size(min = 11, max = 11, message = "CPF com formatação Invalida")
	@Column(name = "cpf", unique = true)
	private String cpf;

	@NotBlank
	@Size(min = 11, max = 11, message = "CNPJ com formatação Invalida")
	@Column(name = "cnpj", unique = true)
	private String cnpj;
	
	@NotNull
	@Column(name = "dataNascimento")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataNascimento;

	@Embedded
	private Contato contato;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "statusPessoa")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private StatusPessoa statusPessoa;
	
	@Column(name = "ativo")
	@NotNull
	private boolean ativo;


	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "tipoPessoa")
	private TipoPessoa tipoPessoa;
	
	@JoinColumn(name = "id", referencedColumnName = "id")
	@OneToOne(cascade = CascadeType.ALL ,fetch = FetchType.EAGER)
	private Endereco endereco;


	
}
