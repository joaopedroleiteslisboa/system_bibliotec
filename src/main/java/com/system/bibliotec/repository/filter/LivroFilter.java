package com.system.bibliotec.repository.filter;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class LivroFilter {

    private String nome;

    private String isbn;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataPublicacaoDe;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
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
