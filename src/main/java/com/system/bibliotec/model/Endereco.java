package com.system.bibliotec.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
@Table(name = "enderecos")
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@EqualsAndHashCode.Include
	private Long id;

	@Column(name = "cep")
	@Size(min = 8, max = 10)
	private String cep;

	@NotBlank(message = "O campo Logradouro é Obrigatório")
	@Size(min = 4, max = 200)
	@Column(name = "logradouro")
	private String logradouro;

	@Column(name = "numero")
	@NotBlank(message = "O Campo numero é obrigatorio")
	private String numero;

	@Column(name = "complemento", length = 100)
	private String complemento;

	@NotBlank(message = "O Campo Bairro é Necessario")
	@Column(name = "bairro")
	@Size(min = 4, max = 60)
	private String bairro;

	@NotBlank(message = "O Campo Cidade é Necessario")
	@Size(min = 3, max = 40)
	@Column(name = "cidade")
	private String cidade;

	@NotBlank(message = "O Campo UF é Necessario. Ex: PB, PE RJ")
	@Size(min = 2, max = 2)
	@Column(name = "uf")
	private String uf;

	@Column(name = "ibge")
	private String ibge;
	
	@JoinColumn(name = "id")
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = false)
	private Cliente idCliente;
	
	
}
