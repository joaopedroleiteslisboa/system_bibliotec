package com.system.bibliotec.repository.livro;

import com.system.bibliotec.model.Livro;
import com.system.bibliotec.model.Livro_;
import com.system.bibliotec.repository.dto.projection.ResumoLivro;
import com.system.bibliotec.repository.filter.LivroFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

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
			predicates.add(builder.like(builder.lower(root.get(Livro_.nome)),
					"%" + livroFilter.getNome().toLowerCase() + "%"));
		}

		if (!StringUtils.isEmpty(livroFilter.getIsbn())) {
			predicates.add(builder.like(builder.lower(root.get(Livro_.isbn13)),
					"%" + livroFilter.getIsbn().toLowerCase() + "%"));
		}

		if (livroFilter.getDataPublicacaoDe() != null) {
			predicates.add(
					builder.greaterThanOrEqualTo(root.get(Livro_.dataPublicacao), livroFilter.getDataPublicacaoDe()));
		}

		if (livroFilter.getDataPublicacaoAte() != null) {
			predicates.add(
					builder.lessThanOrEqualTo(root.get(Livro_.dataPublicacao), livroFilter.getDataPublicacaoAte()));
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

	// TODO: Fica o desafio para incluir categorias e editoras neste resumo... cujo
	// ambos é um Set<>
	@Override
	public Page<ResumoLivro> resumo(LivroFilter livroFilter, Pageable pageable) {
		// TODO Auto-generated method stub
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ResumoLivro> criteria = builder.createQuery(ResumoLivro.class);
		Root<Livro> root = criteria.from(Livro.class);

		criteria.select(builder.construct(ResumoLivro.class, root.get(Livro_.id), root.get(Livro_.nome),
				root.get(Livro_.edicao), root.get(Livro_.descricao), root.get(Livro_.isbn13),
				root.get(Livro_.numeroPaginas), root.get(Livro_.dataPublicacao), root.get(Livro_.valorUnitario)));

		Predicate[] predicates = criarRestricoes(livroFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<ResumoLivro> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(livroFilter));
	}

}
