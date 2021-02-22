package com.system.bibliotec.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.system.bibliotec.model.Livro;
import com.system.bibliotec.repository.LivroRepository;
import com.system.bibliotec.repository.UsuarioRepository;
import com.system.bibliotec.service.operacoes.IOperacaoLivro;

@Service
public class LivroService {


    private final IOperacaoLivro operacao;


    @Autowired
    public LivroService(IOperacaoLivro operacao) {

        this.operacao = operacao;
    }


    public Livro save(Livro livro) {

        return operacao.save(livro);

    }

    public Livro updateLivro(Long id, Livro livro) {

        return operacao.updateLivro(id, livro);
    }

    public void updatePropertyIsbn13Livro(Long id, String isbn13) {
        operacao.updatePropertyIsbn13Livro(id, isbn13);

    }

    public void decrescentarEstoque(Long idLivro, int quantidade) {
        operacao.decrescentarEstoque(idLivro, quantidade);
    }

    public void acrescentarEstoque(Long idLivro, int quantidade) {
        operacao.acrescentarEstoque(idLivro, quantidade);
    }

    public void deleteLivro(Long id) {
        operacao.deleteLivro(id);

    }

    public Livro findByIdLivro(Long id) {
        return operacao.findByIdLivro(id);
    }


}
