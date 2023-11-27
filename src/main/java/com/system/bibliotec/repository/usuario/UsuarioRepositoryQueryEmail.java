package com.system.bibliotec.repository.usuario;


import com.system.bibliotec.model.Usuario;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface UsuarioRepositoryQueryEmail {

    public Usuario findOneByEmail(String email);


}
