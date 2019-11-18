package com.system.bibliotec.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

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
