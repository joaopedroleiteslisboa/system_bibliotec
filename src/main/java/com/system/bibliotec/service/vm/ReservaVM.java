package com.system.bibliotec.service.vm;

import java.util.List;
import java.util.stream.Collectors;

import com.system.bibliotec.model.Reservas;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter			/* View Model*/
public class ReservaVM {

	private Long codigo;

	private String statusReserva;

	private String horaReserva;

	private String dataReserva;

	private String dataLimite;

	private String livro;

	private String ed;

	private String editora;

	private List<String> autor;
	

	public ReservaVM(Reservas entidade) {

		this.codigo = entidade.getId();
		this.statusReserva = entidade.getStatus().toString();
		this.horaReserva = entidade.getHoraReserva().toString();
		this.dataReserva = entidade.getDataReserva().toString();
		this.dataLimite = entidade.getDataPrevisaoTermino().toString();
		this.livro = entidade.getLivro().getNome();
		this.ed = entidade.getLivro().getEdicao();
		this.editora = entidade.getLivro().getEditora().getNome();
		this.autor =  entidade.getLivro().getAutores().stream().map(a -> a.getNome()).collect(Collectors.toList());

	}

}
