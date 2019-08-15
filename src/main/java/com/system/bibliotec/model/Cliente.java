package com.system.bibliotec.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.system.bibliotec.model.embeddeds.Contato;
import com.system.bibliotec.model.enums.Genero;
import com.system.bibliotec.model.enums.StatusCliente;
import com.system.bibliotec.model.enums.TipoCliente;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "clientes")
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@EqualsAndHashCode.Include
	private Long id;

	
	@Enumerated(EnumType.STRING)
	@Column(name = "StatusCliente")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private StatusCliente statusCliente;
	
	@Column(name = "ativo")
	@NotNull
	private boolean ativo;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "tipoCliente")
	private TipoCliente tipoCliente;

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

	@NotNull(message = "O Cpf é Obrigatório")
	@Size(min = 11, max = 11, message = "CPF com formatação Invalida")
	@Column(name = "cpf", unique = true)
	private String cpf;

	@NotNull
	@Column(name = "dataNascimento")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataNascimento;

	@Embedded
	private Contato contato;
	
	
	@JoinColumn(name = "id", referencedColumnName = "id")
	@OneToOne(cascade = CascadeType.ALL ,fetch = FetchType.EAGER)
	private Endereco endereco;

	@JsonIgnore
	@Transient
	public boolean isInativo() {
		return !this.ativo;
	}
}
