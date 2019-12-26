package com.system.bibliotec.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.system.bibliotec.model.enums.StatusLocacao;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;

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
	private int quantidadeDeRenovacao;

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
	@Column(name = "dataPrevisaoTerminoLocacao")
	private LocalDate dataPrevisaoTerminoLocacao;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@JsonFormat(pattern = "kk:mm:ss")
	@DateTimeFormat(pattern = "kk:mm:ss")
	@Column(name = "horaCancelamento")
	private LocalTime horaCancelamento;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "dataCancelamento")
	private LocalDate dataCancelamento;

	
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@JsonFormat(pattern = "kk:mm:ss")
	@DateTimeFormat(pattern = "kk:mm:ss")
	@Column(name = "horaEncerramento")
	private LocalTime horaEncerramento;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "dataEncerramento")
	private LocalDate dataEncerramento;

	
	@OneToOne
	@Size(min = 1, message = "O Locacao precisar ter no minimo um Cliente")
	@JoinColumn(name = "idCliente")
	private Cliente cliente;

	@OneToOne
	@Size(min = 1, max = 1, message = "O maximo de livros permitidos é 1 Exemplar")
	@JoinColumn(name = "idLivro")
	private Livro livro;

	@Column(name = "observacoesEntrega")
	@Size(max = 100)
	private String observacoesEntrega;
	
	@Column(name = "observacoesDevolucao")
	@Size(max = 100)
	private String observacoesDevolucao;
	
}
