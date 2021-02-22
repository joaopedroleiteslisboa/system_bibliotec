package com.system.bibliotec.service.validation;

import org.springframework.stereotype.Component;

import com.system.bibliotec.model.AbstractAuditingEntity;
import com.system.bibliotec.repository.ReservaRepository;

public interface ITriagemReservaELocacao<E extends AbstractAuditingEntity,
        L extends AbstractAuditingEntity, ID extends Long, U extends AbstractAuditingEntity> {


    void triagemReservaELocacao(E t, L l, ID id, U u);




    /*
     * default <X extends AbstractAuditingEntity> X triagemLocacao(E t, L l, ID id)
     * {
     *
     * triagemReservaELocacao(t,l,id);
     *
     *
     *
     * return null; }
     */


}
