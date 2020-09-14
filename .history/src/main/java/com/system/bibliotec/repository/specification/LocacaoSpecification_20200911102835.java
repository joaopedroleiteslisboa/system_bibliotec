package com.system.bibliotec.repository.specification;

import java.time.LocalDate;
import java.time.LocalTime;

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

	public static Specification<Locacoes> porIntervaloData(LocalDate inicio, LocalDate fim) {
		if (inicio == null || fim == null) {
			return null;
		} else {
			return (root, query, cb) -> cb.between(root.get(Locacoes_.DATA_LOCACAO), inicio, fim);
		}
    }
    
    
	public static Specification<Locacoes> porIntervaloHora(LocalTime inicio, LocalTime fim) {
		if (inicio == null || fim == null) {
			return null;
		} else {
			return (root, query, cb) -> cb.between(root.get(Locacoes_.DATA_LOCACAO), inicio, fim);
		}
    }
    

	public static Specification<Locacoes> porEmpresa(Long empresa) {
		if (empresa == null) {
			return null;
		} else {
			return (root, query, cb) -> {
				Join<Object, Object> join = root.join("empresa");
				return cb.equal(join.get("id"), empresa);
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