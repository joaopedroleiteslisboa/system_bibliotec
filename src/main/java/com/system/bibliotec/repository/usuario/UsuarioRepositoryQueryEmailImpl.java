package com.system.bibliotec.repository.usuario;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Component;

import com.system.bibliotec.exception.UsuarioNaoEncontrado;
import com.system.bibliotec.model.Usuario;
import com.system.bibliotec.model.Usuario_;

@Component
public class UsuarioRepositoryQueryEmailImpl implements UsuarioRepositoryQueryEmail {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Usuario findOneByEmail(String email) {
        // TODO Auto-generated method stub

        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Usuario> criteriaQuery = builder.createQuery(Usuario.class);
        Root<Usuario> root = criteriaQuery.from(Usuario.class);
        criteriaQuery.select(root);
        criteriaQuery.where(builder.equal(root.get(Usuario_.email), email));
        TypedQuery<Usuario> typedQuery = manager.createQuery(criteriaQuery);
        Usuario user = null;
        try {
            user = typedQuery.getSingleResult();
        } catch (Exception e) {
            // TODO: handle exception
            throw new UsuarioNaoEncontrado("Usuario invalido ou inexistente");
        }

        return (user != null) ? user : null;
    }


}
