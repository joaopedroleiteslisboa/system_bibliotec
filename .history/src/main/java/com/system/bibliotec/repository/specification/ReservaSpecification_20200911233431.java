package com.system.bibliotec.repository.specification;

import org.springframework.data.jpa.domain.Specification;

public class ReservaSpecification {

    

    public static Specification<Locacoes> porID(Long id) {
		if (id == null) {
			return null;
		} else {
			return (root, query, cb) -> cb.equal(root.get(AbstractAuditingEntity_.ID), id);
		}
	}



    
}