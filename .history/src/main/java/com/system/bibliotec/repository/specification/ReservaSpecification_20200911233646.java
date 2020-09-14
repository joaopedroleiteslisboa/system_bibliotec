package com.system.bibliotec.repository.specification;

import javax.persistence.criteria.Join;

import com.system.bibliotec.model.AbstractAuditingEntity_;
import com.system.bibliotec.model.Reservas;
import com.system.bibliotec.model.Reservas_;

import org.springframework.data.jpa.domain.Specification;

import ch.qos.logback.core.status.Status;

public class ReservaSpecification {

    

    public static Specification<Reservas> porID(Long id) {
		if (id == null) {
			return null;
		} else {
			return (root, query, cb) -> cb.equal(root.get(AbstractAuditingEntity_.ID), id);
		}
	}


    public static Specification<Reservas> porStatus(Status status) {
		if (status == null) {
			return null;
		} else {
			return (root, query, cb) -> cb.equal(root.get(Reservas_.STATUS), status);
		}
    }
    
    public static Specification<Reservas> porLivroId(Long idLivro) {
		if (idLivro == null) {
			return null;
		} else {
			return (root, query, cb) -> {
				Join<Object, Object> join = root.join(Reservas_.LIVRO);
				return cb.equal(join.get(AbstractAuditingEntity_.ID), idLivro);
			};
		}
	}

    public static Specification<Reservas> porUsuarioId(Long idCliente) {
		if (idCliente == null) {
			return null;
		} else {
			return (root, query, cb) -> {
				Join<Object, Object> join = root.join(Reservas_.USUARIO);
				return cb.equal(join.get(AbstractAuditingEntity_.ID), idCliente);
			};
		}
	}

	public static Specification<Reservas> porUsuarioContexto(String userName) {
		if (userName == null || userName.isEmpty()) {
			return null;
		} else {
			return (root, query, cb) -> {
				Join<Object, Object> join = root.join(Reservas_.USUARIO);
				return cb.equal(join.get(AbstractAuditingEntity_.CREATED_BY), userName);
			};
		}
	}


    
}