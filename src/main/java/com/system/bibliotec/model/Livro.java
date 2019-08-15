package com.system.bibliotec.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.system.bibliotec.model.enums.Idioma;
import com.system.bibliotec.model.enums.StatusLivro;

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
@Table(name = "livros")
public class Livro {

	@Id
	@GeneratedValue
	@Column(name = "id")
	@EqualsAndHashCode.Include
	private Long id;

	@Column(name = "codBarras", length = 13)
	private String codBarras;

	@Size(max = 256)
	@Column(name = "imagenUrl", length = 256)
	private String imagenUrl;

	@NotBlank(message = "Digite um nome de livro")
	@Size(max = 200, message = "Nome de livro muito grande")
	@Column(name = "nome")
	private String nome;

	@JsonManagedReference
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "livro_has_autores", joinColumns = { @JoinColumn(name = "id_livro") }, inverseJoinColumns = {
			@JoinColumn(name = "id_autor") })
	private Set<Autor> autores = new HashSet<Autor>();

	@JsonManagedReference
	@JoinColumn(name = "idEditora")
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private Editora editora;

	@NotNull(message = "Este campo é obrigatorio")
	@Column(name = "edicao")
	private String edicao;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@Enumerated(EnumType.STRING)
	@Column(name = "statusLivro")
	private StatusLivro statusLivro;

	@Enumerated(EnumType.STRING)
	@Column(name = "idioma")
	private Idioma idioma;

	@JsonManagedReference
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "livro_has_categorias", joinColumns = { @JoinColumn(name = "id_livro") }, inverseJoinColumns = {
			@JoinColumn(name = "id_categoria") })
	private Set<Categoria> categorias = new HashSet<Categoria>();

	@Size(max = 8388607, min = 3, message = "Descrição de livro muito grande")
	@Column(name = "descricao")
	private String descricao;

	@NotNull(message = "Insira o ISBN do livro")
	@Column(name = "isbn13")
	private String isbn13;

	@NotNull(message = "O livro deve ter o numero de paginas")
	@Min(value = 1, message = "O livro deve ter no minimo 1 pagina")
	@Column(name = "numeroPaginas")
	private Integer numeroPaginas;

	@NotNull(message = "Insira uma data de publicação")
	@Past(message = "A data deve ser inferior a atual")
	@Column(name = "dataPublicacao")
	@JsonFormat(pattern = "dd-MM-yyyy")
	@DateTimeFormat(pattern = "dd-MM-yyyy") 
	private LocalDate dataPublicacao;

	@DecimalMin(value = "1.00", message = "O livro deve ter um preço valido")
	@NotNull(message = "Insira um preço")
	@Column(name = "valorUnitario",precision = 10, scale = 2)
	private BigDecimal valorUnitario = BigDecimal.ZERO; //TODO: Modificar esse default value em implementação para produção...

	@NotNull(message = "Informe uma quantidade adicionada em seu Estoque de Livros")
	@Column(name = "quantidade")
	@Size(min = 1, message = "Informe pelo menos {min} livro para Salvar no Estoque")
	private Integer quantidade;

	
}
