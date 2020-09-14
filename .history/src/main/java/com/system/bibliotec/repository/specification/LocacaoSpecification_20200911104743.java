package com.system.bibliotec.repository.specification;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.criteria.Join;

import com.google.common.base.Strings;
import com.system.bibliotec.model.AbstractAuditingEntity_;
import com.system.bibliotec.model.Livro_;
import com.system.bibliotec.model.Locacoes;
import com.system.bibliotec.model.Locacoes_;
import com.system.bibliotec.model.Usuario_;
import com.system.bibliotec.model.enums.Status;

import org.springframework.data.jpa.domain.Specification;

/**
 * LocacaoSpecification - Criteria Querys...
 */
public class LocacaoSpecification {

    
    public static Specification<Locacoes> porID(Long id) {
		if (id == null) {
			return null;
		} else {
			return (root, query, cb) -> cb.equal(root.get(AbstractAuditingEntity_.ID), id);
		}
	}

    public static Specification<Locacoes> porStatus(Status status) {
		if (status == null) {
			return null;
		} else {
			return (root, query, cb) -> cb.equal(root.get(Locacoes_.status), status);
		}
	}

	public static Specification<Locacoes> porIntervaloDataCriacao(LocalDate inicio, LocalDate fim) {
		if (inicio == null || fim == null) {
			return null;
		} else {
			return (root, query, cb) -> cb.between(root.get(Locacoes_.dataLocacao), inicio, fim);
		}
    }
    
    
	public static Specification<Locacoes> porIntervaloHoraCriacao(LocalTime inicio, LocalTime fim) {
		if (inicio == null || fim == null) {
			return null;
		} else {
			return (root, query, cb) -> cb.between(root.get(Locacoes_.horaLocacao), inicio, fim);
		}
    }


    public static Specification<Locacoes> porIntervaloDataCancelamento(LocalDate inicio, LocalDate fim) {
		if (inicio == null || fim == null) {
			return null;
		} else {
			return (root, query, cb) -> cb.between(root.get(Locacoes_.dataCancelamento), inicio, fim);
		}
    }
    
    
	public static Specification<Locacoes> porIntervaloHoraCancelamento(LocalTime inicio, LocalTime fim) {
		if (inicio == null || fim == null) {
			return null;
		} else {
			return (root, query, cb) -> cb.between(root.get(Locacoes_.horaCancelamento), inicio, fim);
		}
    }
    

	public static Specification<Locacoes> porLivroId(Long idLivro) {
		if (idLivro == null) {
			return null;
		} else {
			return (root, query, cb) -> {
				Join<Object, Object> join = root.join("livro");
				return cb.equal(join.get(AbstractAuditingEntity_.ID), idLivro);
			};
		}
	}

    public static Specification<Locacoes> porUsuarioId(Long idCliente) {
		if (idCliente == null) {
			return null;
		} else {
			return (root, query, cb) -> {
				Join<Object, Object> join = root.join("usuario");
				return cb.equal(join.get(AbstractAuditingEntity_.ID), idCliente);
			};
		}
	}
}