package com.system.bibliotec.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.system.bibliotec.model.enums.StatusLocacao;

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
@Table(name = "locacoes")
public class Locacao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@EqualsAndHashCode.Include
	private Long id;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@Enumerated(EnumType.STRING)
	@Column(name = "statusLocacao")
	private StatusLocacao statusLocacao;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@Column(name = "quantidadeDeRenovacao")
	@Size(max = 3)
	private int quantidadeDeRenovacao = 0;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@JsonFormat(pattern = "kk:mm:ss")
	@DateTimeFormat(pattern = "kk:mm:ss")
	@Column(name = "horaLocacao")
	private LocalTime horaLocacao;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "dataLocacao")
	private LocalDate dataLocacao;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "dataTerminoLocacao")
	private LocalDate dataTerminoLocacao;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@JsonFormat(pattern = "kk:mm:ss")
	@DateTimeFormat(pattern = "kk:mm:ss")
	@Column(name = "horaCancelamentoLocacao")
	private LocalTime horaCancelamentoLocacao;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "dataCancelamentoLocacao")
	private LocalDate dataCancelamentoLocacao;

	@OneToOne
	@Size(min = 1, message = "O Locacao precisar ter no minimo um Cliente")
	@JoinColumn(name = "idCliente")
	private Cliente cliente;

	@OneToOne
	@Size(min = 1, max = 1, message = "O maximo de livros permitidos é 1 Exemplar")
	@JoinColumn(name = "idLivro")
	private Livro livro;

	@Column(name = "observacoesDeEntrega")
	@Size(max = 100)
	private String observacoesDeEntrega;
	
	@Column(name = "observacoesDeDevolucao")
	@Size(max = 100)
	private String observacoesDeDevolucao;
	
}
