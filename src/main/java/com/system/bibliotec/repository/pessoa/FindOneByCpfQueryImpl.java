package com.system.bibliotec.repository.pessoa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
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
        criteriaQuery.where(builder.equal(root.get(Pessoa_.CPF), cpf));
        TypedQuery<Pessoa> typedQuery = manager.createQuery(criteriaQuery);
        Pessoa c = null;
        try {
            c = typedQuery.getSingleResult();
        } catch (Exception e) {
            // TODO: handle exception
            throw new PessoaInexistenteException("Pessoa invalida ou nao encontrada na Base de dados");
        }
        return c;
    }

}
