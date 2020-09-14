package com.system.bibliotec.repository.specification;

import com.system.bibliotec.model.Solicitacoes;

public class SolicitacaoSpecification {
    

    public static Specification<Solicitacoes> porID(Long id) {
		if (id == null) {
			return null;
		} else {
			return (root, query, cb) -> cb.equal(root.get(AbstractAuditingEntity_.ID), id);
		}
	}

}