package com.system.bibliotec.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.system.bibliotec.model.enums.StatusCliente;
import com.system.bibliotec.model.enums.TipoCliente;

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
@Entity
@Table(name = "clientes")
public class Cliente extends Pessoa{
		
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

	
	@JsonIgnore
	@Transient
	public boolean isInativo() {
		return !this.ativo;
	}
}
