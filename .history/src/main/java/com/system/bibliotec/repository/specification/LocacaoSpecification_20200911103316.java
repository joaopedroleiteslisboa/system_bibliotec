package com.system.bibliotec.repository.specification;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.criteria.Join;

import com.google.common.base.Strings;
import com.system.bibliotec.model.Locacoes;
import com.system.bibliotec.model.Locacoes_;
import com.system.bibliotec.model.enums.Status;

import org.springframework.data.jpa.domain.Specification;

/**
 * LocacaoSpecification
 */
public class LocacaoSpecification {

    
    public static Specification<Locacoes> porID(Long id) {
		if (id == null) {
			return null;
		} else {
			return (root, query, cb) -> cb.equal(root.get("id"), id);
		}
	}

	public static Specification<Locacoes> porStatus(Status status) {
		if (status == null) {
			return null;
		} else {
			return (root, query, cb) -> cb.equal(root.get(Locacoes_.STATUS, status);
		}
	}

	public static Specification<Locacoes> porIntervaloDataCriacao(LocalDate inicio, LocalDate fim) {
		if (inicio == null || fim == null) {
			return null;
		} else {
			return (root, query, cb) -> cb.between(root.get(Locacoes_.DATA_LOCACAO), inicio, fim);
		}
    }
    
    
	public static Specification<Locacoes> porIntervaloHoraCriacao(LocalTime inicio, LocalTime fim) {
		if (inicio == null || fim == null) {
			return null;
		} else {
			return (root, query, cb) -> cb.between(root.get(Locacoes_.HORA_LOCACAO), inicio, fim);
		}
    }


    public static Specification<Locacoes> porIntervaloDataCancelamento(LocalDate inicio, LocalDate fim) {
		if (inicio == null || fim == null) {
			return null;
		} else {
			return (root, query, cb) -> cb.between(root.get(Locacoes_.DATA_CANCELAMENTO), inicio, fim);
		}
    }
    
    
	public static Specification<Locacoes> porIntervaloHoraCancelamento(LocalTime inicio, LocalTime fim) {
		if (inicio == null || fim == null) {
			return null;
		} else {
			return (root, query, cb) -> cb.between(root.get(Locacoes_.HORA_CANCELAMENTO), inicio, fim);
		}
    }
    

	public static Specification<Locacoes> porLivro(Long idLivro) {
		if (idLivro == null) {
			return null;
		} else {
			return (root, query, cb) -> {
				Join<Object, Object> join = root.join("livro");
				return cb.equal(join.get("id"), idLivro);
			};
		}
	}

	public static Specification<Auditoria> porReparadora(Long reparadora) {
		if (reparadora == null) {
			return null;
		} else {
			return (root, query, cb) -> {
				Join<Object, Object> join = root.join("reparadora");
				return cb.equal(join.get("id"), reparadora);
			};
		}
	}
}