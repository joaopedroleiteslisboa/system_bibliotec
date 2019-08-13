package com.system.bibliotec.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@Table(name = "autores")
public class Autor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@EqualsAndHashCode.Include
	private Long id;
	
	@NotBlank(message = "O nome é Obrigatorio")
	@Size(min = 3, max = 60)
	@Column(name = "nome")
	private String nome;
	
	@NotBlank(message = "Uma descricao seria ideal")
	@Size(max = 2000)
	@Column(name = "descricao")
	private String descricao;

	@JsonBackReference
	@ManyToMany(mappedBy = "autores", fetch = FetchType.EAGER)
	private List<Livro> livros;

	
}
