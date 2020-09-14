package com.system.bibliotec.repository.specification;

import com.system.bibliotec.model.Locacoes;

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

	public static Specification<Auditoria> porLote(String lote) {
		if (Strings.isNullOrEmpty(lote)) {
			return null;
		} else {
			return (root, query, cb) -> cb.like(cb.upper(root.get("lote")), "%" + lote.toUpperCase() + "%");
		}
	}

	public static Specification<Auditoria> porStatus(StatusAuditoria status) {
		if (status == null) {
			return null;
		} else {
			return (root, query, cb) -> cb.equal(root.get("status"), status);
		}
	}

	public static Specification<Auditoria> porAuditor(Long auditor) {
		if (auditor == null) {
			return null;
		} else {
			return (root, query, cb) -> cb.equal(root.get("idUsuarioAuditor"), auditor);
		}
	}

	public static Specification<Auditoria> porIntervalo(LocalDate inicio, LocalDate fim) {
		if (inicio == null || fim == null) {
			return null;
		} else {
			return (root, query, cb) -> cb.between(root.get("dataAuditoria"), inicio, fim);
		}
	}

	public static Specification<Auditoria> porEmpresa(Long empresa) {
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