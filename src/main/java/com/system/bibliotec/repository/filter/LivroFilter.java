package com.system.bibliotec.repository.filter;

import java.time.LocalDate;

public class LivroFilter {

	private String nome;

	private String isbn;

	private LocalDate dataPublicacaoDe;

	private LocalDate dataPublicacaoAte;



	public LocalDate getDataPublicacaoDe() {
		return dataPublicacaoDe;
	}

	public void setDataPublicacaoDe(LocalDate dataPublicacaoDe) {
		this.dataPublicacaoDe = dataPublicacaoDe;
	}

	public LocalDate getDataPublicacaoAte() {
		return dataPublicacaoAte;
	}

	public void setDataPublicacaoAte(LocalDate dataPublicacaoAte) {
		this.dataPublicacaoAte = dataPublicacaoAte;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

}
