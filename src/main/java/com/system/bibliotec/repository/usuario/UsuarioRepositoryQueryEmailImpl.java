package com.system.bibliotec.repository.usuario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Component;

import com.system.bibliotec.exception.UsuarioNaoEncontrado;
import com.system.bibliotec.model.Usuario;
import com.system.bibliotec.model.Usuario_;
import org.springframework.stereotype.Repository;

@Repository
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
        criteriaQuery.where(builder.equal(root.get(Usuario_.EMAIL), email));
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
