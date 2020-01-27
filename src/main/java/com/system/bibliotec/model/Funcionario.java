package com.system.bibliotec.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.system.bibliotec.model.enums.StatusFuncionario;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "funcionarios")
public class Funcionario extends Pessoa{
	
		
	@Column(name = "matricula", unique = true, nullable = false)
	private String matricula;
	
	@JoinColumn(name = "id", referencedColumnName = "id")
	@OneToOne(fetch = FetchType.EAGER)
	private Cargo cargo;
	
	@Column(name = "ativo", nullable = false)
	private boolean ativo;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "statusFuncionario", nullable = false)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private StatusFuncionario statusFuncionario;
	

	
	

}
