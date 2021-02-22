package com.system.bibliotec.repository.pessoa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Component;

import com.system.bibliotec.exception.PessoaInexistenteException;
import com.system.bibliotec.model.Pessoa;
import com.system.bibliotec.model.Pessoa_;


@Component
public class FindOneByCpfQueryImpl implements FindOneByCpfQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Pessoa findOneByCpf(String cpf) {
        // TODO Auto-generated method stub
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Pessoa> criteriaQuery = builder.createQuery(Pessoa.class);
        Root<Pessoa> root = criteriaQuery.from(Pessoa.class);
        criteriaQuery.select(root);
        criteriaQuery.where(builder.equal(root.get(Pessoa_.cpf), cpf));
        TypedQuery<Pessoa> typedQuery = manager.createQuery(criteriaQuery);
        Pessoa c = null;
        try {
            c = typedQuery.getSingleResult();
        } catch (Exception e) {
            // TODO: handle exception
            throw new PessoaInexistenteException("Pessoa invalida ou nao encontrada na Base de dados");
        }
        return (c != null) ? c : null;
    }

}
