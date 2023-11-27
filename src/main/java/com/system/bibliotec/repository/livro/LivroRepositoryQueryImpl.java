package com.system.bibliotec.repository.livro;

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

import com.system.bibliotec.model.Livro;
import com.system.bibliotec.model.Livro_;
import com.system.bibliotec.repository.dto.projection.ResumoLivro;
import com.system.bibliotec.repository.filter.LivroFilter;

public class LivroRepositoryQueryImpl implements LivroRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<Livro> filtrar(LivroFilter livroFilter, Pageable pageable) {
        // TODO Auto-generated method stub
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Livro> criteria = builder.createQuery(Livro.class);
        Root<Livro> root = criteria.from(Livro.class);

        Predicate[] predicates = criarRestricoes(livroFilter, builder, root);
        criteria.where(predicates);

        TypedQuery<Livro> query = manager.createQuery(criteria);
        adicionarRestricoesDePaginacao(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, total(livroFilter));
    }

    private Predicate[] criarRestricoes(LivroFilter livroFilter, CriteriaBuilder builder, Root<Livro> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (!StringUtils.isEmpty(livroFilter.getNome())) {
            predicates.add(builder.like(builder.lower(root.get(Livro_.NOME)),
                    "%" + livroFilter.getNome().toLowerCase() + "%"));
        }

        if (!StringUtils.isEmpty(livroFilter.getIsbn())) {
            predicates.add(builder.like(builder.lower(root.get(Livro_.ISBN13)),
                    "%" + livroFilter.getIsbn().toLowerCase() + "%"));
        }

        if (livroFilter.getDataPublicacaoDe() != null) {
            predicates.add(
                    builder.greaterThanOrEqualTo(root.get(Livro_.DATA_PUBLICACAO), livroFilter.getDataPublicacaoDe()));
        }

        if (livroFilter.getDataPublicacaoAte() != null) {
            predicates.add(
                    builder.lessThanOrEqualTo(root.get(Livro_.DATA_PUBLICACAO), livroFilter.getDataPublicacaoAte()));
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

    private Long total(LivroFilter LivroFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Livro> root = criteria.from(Livro.class);

        Predicate[] predicates = criarRestricoes(LivroFilter, builder, root);
        criteria.where(predicates);

        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }


    @Override
    public Page<ResumoLivro> resumo(LivroFilter livroFilter, Pageable pageable) {
        // TODO Auto-generated method stub
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<ResumoLivro> criteria = builder.createQuery(ResumoLivro.class);
        Root<Livro> root = criteria.from(Livro.class);

        criteria.select(builder.construct(ResumoLivro.class, root.get(Livro_.ID), root.get(Livro_.NOME),
                root.get(Livro_.EDICAO), root.get(Livro_.DESCRICAO), root.get(Livro_.ISBN13),
                root.get(Livro_.NUMERO_PAGINAS), root.get(Livro_.DATA_PUBLICACAO), root.get(Livro_.VALOR_UNITARIO)));

        Predicate[] predicates = criarRestricoes(livroFilter, builder, root);
        criteria.where(predicates);

        TypedQuery<ResumoLivro> query = manager.createQuery(criteria);
        adicionarRestricoesDePaginacao(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, total(livroFilter));
    }

}
