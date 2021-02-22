package com.system.bibliotec.service.vm;

import java.util.List;
import java.util.stream.Collectors;

import com.system.bibliotec.model.Locacoes;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter                                 /* View Model*/
public class LocacaoVM {


    private Long codigo;
    private String statusLocacao;
    private String quantidadeDeRenovacao;
    private String horaLocacao;
    private String dataLocacao;
    private String dataTerminoLocacao;
    private String titularLocacao;
    private String livro;
    private List<String> autor;
    private String observacoesEntrega;


    public LocacaoVM(Locacoes entidade) {
        this.codigo = entidade.getId();
        this.statusLocacao = entidade.getStatus().toString();
        this.quantidadeDeRenovacao = String.valueOf(entidade.getQuantidadeDeRenovacao());
        this.horaLocacao = entidade.getHoraLocacao().toString();
        this.dataLocacao = entidade.getDataLocacao().toString();
        this.dataTerminoLocacao = entidade.getDataPrevisaoTermino().toString();
        this.titularLocacao = entidade.getUsuario().getNome();
        this.livro = entidade.getLivro().getNome();
        this.observacoesEntrega = entidade.getObservacoesEntrega();
        this.autor = entidade.getLivro().getAutores().stream().map(a -> a.getNome()).collect(Collectors.toList());

    }


}
