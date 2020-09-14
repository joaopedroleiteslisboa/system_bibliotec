package com.system.bibliotec.service.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.system.bibliotec.config.ConstantsUtils;
import com.system.bibliotec.exception.LimiteOperacaoAtingidaException;
import com.system.bibliotec.exception.OperacaoJaAtivadaParaUsuarioException;
import com.system.bibliotec.exception.ReservaLocadaException;
import com.system.bibliotec.model.AbstractAuditingEntity;
import com.system.bibliotec.model.Locacoes;
import com.system.bibliotec.model.Reservas;
import com.system.bibliotec.repository.GenericRepository;
import com.system.bibliotec.repository.LocacaoRepository;
import com.system.bibliotec.repository.ReservaRepository;

@Component("triagemReservaELocacaoImpl")
public class TriagemReservaELocacaoImpl<E extends AbstractAuditingEntity, L extends AbstractAuditingEntity, 
ID extends Long, U extends AbstractAuditingEntity>
		implements ITriagemReservaELocacao<E, L, ID, U> { //E= Entity (locacao ou reserva), L = objeto Livro, ID = Id livro contexto, U = usuario contexto

	//private GenericRepository<E, Long> genericRepository;
	
	@Autowired
	private LocacaoRepository lRepository;
	
	@Autowired
	private ReservaRepository rRepository;

	@Override
	public void triagemReservaELocacao(E e, L l, ID id, U u) {

		if (e instanceof Reservas) {

			if (operacaoReservaJaAtivadaParaContextoUsuarioELivro(ConstantsUtils.DEFAULT_VALUE_STATUS_ATIVA.toString(), id) >= 1) {
				throw new OperacaoJaAtivadaParaUsuarioException( u.saudacoes() + 
						" Operação não realizada. Detectamos uma Reserva em vingência em nossa base de dados do Examplar "+ l.getNome());
			}

			if (quantidadeDeOperacaoReservaAtivaParaContextoUsuarioELivro(
					ConstantsUtils.DEFAULT_VALUE_STATUS_ATIVA.toString()) > 2) {
				throw new LimiteOperacaoAtingidaException(u.saudacoes() + 
						" O Limite de Reservas foi Atingido. Por gentileza, cancele alguma ou inicie um processo de Locação");

			}
			if (locacaoAtivaParaContextoDoUsarioELivroEReserva(ConstantsUtils.DEFAULT_VALUE_STATUS_ATIVA.toString(), id) >= 1) {
				throw new ReservaLocadaException(u.saudacoes() + 
						" Consta uma Locacão em vigência do exemplar "
						+ l.getNome()
						+ ". Desta forma não é possivel realizar estar operação de Reserva ate a data de Vencimento da Locação.");
			}
		} else if (e instanceof Locacoes) {

			if (operacaoLocacaoJaAtivadaParaContextoUsuarioELivro(id, u.getId()) >= 1) {
				throw new OperacaoJaAtivadaParaUsuarioException(u.saudacoes() + 
						" Operação não Permitida. Detectamos uma Locação em vingência em nossa base de dados do Examplar "+ l.getNome());
			}

			if (quantidadeDeOperacaoLocacaoAtivaParaContextoUsuarioELivro(
					ConstantsUtils.DEFAULT_VALUE_STATUS_ATIVA.toString(), u.getId()) > 2) {
				throw new LimiteOperacaoAtingidaException(u.saudacoes() + 
						" O Limite de Locação foi Atingido. Por gentileza, cancele alguma ou aguarde ate o termino das vigências");

			}

		}

	}

	private int operacaoReservaJaAtivadaParaContextoUsuarioELivro(String statusReserva, Long idLivro) {
		return rRepository.isAtivaToUserContextAndlivro(idLivro);
	}

	private int operacaoLocacaoJaAtivadaParaContextoUsuarioELivro(Long idLivro, long idUsuario) {
		return lRepository.isAtivaToUserContextAndlivro(idLivro, idUsuario);
	}

	private int quantidadeDeOperacaoReservaAtivaParaContextoUsuarioELivro(String status) {

		return rRepository.quantidadeStatusDoUsuario(status);
	}

	private int quantidadeDeOperacaoLocacaoAtivaParaContextoUsuarioELivro(String status, long idUsuario) {

		return lRepository.quantidadeStatusDoUsuario(status, idUsuario);
	}

	private int locacaoAtivaParaContextoDoUsarioELivroEReserva(String statusLocacao, Long idLivro) {

		return lRepository.isAtivaToUserContextAndlivro(idLivro);
	}

}
