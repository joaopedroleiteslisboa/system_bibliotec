package com.system.bibliotec.repository.specification;

import com.system.bibliotec.model.AbstractAuditingEntity_;
import com.system.bibliotec.model.Solicitacoes;
import com.system.bibliotec.model.Solicitacoes_;

import org.springframework.data.jpa.domain.Specification;

public class SolicitacaoSpecification {
    

    public static Specification<Solicitacoes> porID(Long id) {
		if (id == null) {
			return null;
		} else {
			return (root, query, cb) -> cb.equal(root.get(AbstractAuditingEntity_.ID), id);
		}
    }
    
    public static Specification<Solicitacoes> porUsuarioId(Long idCliente) {
		if (idCliente == null) {
			return null;
		} else {
			return (root, query, cb) -> {
				Join<Object, Object> join = root.join(Solicitacoes_.USUARIO);
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
				return cb.equal(join.get(Usuario_.EMAIL), userName);
			};
		}
	}
    

}