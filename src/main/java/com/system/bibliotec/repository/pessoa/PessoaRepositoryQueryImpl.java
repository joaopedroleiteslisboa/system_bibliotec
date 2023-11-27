package com.system.bibliotec.repository.pessoa;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.system.bibliotec.model.Pessoa;
import com.system.bibliotec.model.Pessoa_;
import com.system.bibliotec.repository.filter.PessoaFilter;

public class PessoaRepositoryQueryImpl implements PessoaRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<Pessoa> filtrar(PessoaFilter PessoaFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Pessoa> criteria = builder.createQuery(Pessoa.class);
        Root<Pessoa> root = criteria.from(Pessoa.class);

        Predicate[] predicates = criarRestricoes(PessoaFilter, builder, root);
        criteria.where(predicates);

        TypedQuery<Pessoa> query = manager.createQuery(criteria);
        adicionarRestricoesDePaginacao(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, total(PessoaFilter));
    }

    private Predicate[] criarRestricoes(PessoaFilter PessoaFilter, CriteriaBuilder builder, Root<Pessoa> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (!StringUtils.isEmpty(PessoaFilter.getNome())) {
            predicates.add(builder.like(builder.lower(root.get(Pessoa_.NOME)),
                    "%" + PessoaFilter.getNome().toLowerCase() + "%"));
        }

        if (PessoaFilter.getDataNascimento() != null) {
            predicates
                    .add(builder.lessThanOrEqualTo(root.get(Pessoa_.DATA_NASCIMENTO), PessoaFilter.getDataNascimento()));
        }

        if (PessoaFilter.getDataNascimentoDe() != null) {
            predicates.add(
                    builder.greaterThanOrEqualTo(root.get(Pessoa_.DATA_NASCIMENTO), PessoaFilter.getDataNascimentoDe()));
        }

        if (PessoaFilter.getDataNascimentoAte() != null) {
            predicates.add(
                    builder.lessThanOrEqualTo(root.get(Pessoa_.DATA_NASCIMENTO), PessoaFilter.getDataNascimentoAte()));
        }

        return predicates.toArray(new Predicate[predicates.size()]);
    }

    private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
        int paginaAtual = pageable.getPageNumber();
        int totalRegistrosPorPagina = pageable.getPageSize();
        int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;

        query.setFirstResult(primeiroRegistroDaPagina);
        query.setMaxResults(totalRegistrosPorPagina);
    }

    private Long total(PessoaFilter PessoaFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Pessoa> root = criteria.from(Pessoa.class);

        Predicate[] predicates = criarRestricoes(PessoaFilter, builder, root);
        criteria.where(predicates);

        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }
}
