package com.system.bibliotec.repository.cliente;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Component;

import com.system.bibliotec.exception.ClienteInexistenteException;
import com.system.bibliotec.model.Cliente;
import com.system.bibliotec.model.Cliente_;

@Component
public class FindOneByCpfQueryImpl implements FindOneByCpfQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Cliente findOneByCpf(String cpf) {
		// TODO Auto-generated method stub		
		CriteriaBuilder builder = manager.getCriteriaBuilder();		
		CriteriaQuery<Cliente> criteriaQuery = builder.createQuery(Cliente.class);		
		Root<Cliente> root = criteriaQuery.from(Cliente.class);
		criteriaQuery.select(root);		
		criteriaQuery.where(builder.equal(root.get(Cliente_.cpf), cpf));
		TypedQuery<Cliente> typedQuery = manager.createQuery(criteriaQuery);
		Cliente c = null;
		try {
		 c = typedQuery.getSingleResult();
		}catch (Exception e) {
			// TODO: handle exception
			throw new ClienteInexistenteException("Pessoa invalida ou nao encontrada na Base de dados");
		}		
		return (c != null)? c : null;	
	}

}
