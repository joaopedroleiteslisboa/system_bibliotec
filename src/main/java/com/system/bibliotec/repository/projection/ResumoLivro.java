package com.system.bibliotec.repository.projection;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ResumoLivro {


	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEdicao() {
		return edicao;
	}

	public void setEdicao(String edicao) {
		this.edicao = edicao;
	}


	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getIsbn13() {
		return isbn13;
	}

	public void setIsbn13(String isbn13) {
		this.isbn13 = isbn13;
	}

	public Integer getNumeroPaginas() {
		return numeroPaginas;
	}

	public void setNumeroPaginas(Integer numeroPaginas) {
		this.numeroPaginas = numeroPaginas;
	}

	public LocalDate getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(LocalDate dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	private Long id;

	private String nome;

	private String edicao;
		
	private String descricao;

	private String isbn13;

	private Integer numeroPaginas;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataPublicacao;

	private BigDecimal preco;

	public ResumoLivro(Long id, String nome, String edicao,  String descricao,
			String isbn13, Integer numeroPaginas, LocalDate dataPublicacao, BigDecimal preco) {
		super();
		this.id = id;
		this.nome = nome;
		this.edicao = edicao;
		this.descricao = descricao;
		this.isbn13 = isbn13;
		this.numeroPaginas = numeroPaginas;
		this.dataPublicacao = dataPublicacao;
		this.preco = preco;
	}



	
	
	
}
