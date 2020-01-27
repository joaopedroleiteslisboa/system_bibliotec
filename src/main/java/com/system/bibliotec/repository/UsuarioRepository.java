package com.system.bibliotec.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.system.bibliotec.model.Cliente;
import com.system.bibliotec.model.Usuario;
import com.system.bibliotec.repository.usuario.UsuarioRepositoryQueryEmail;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>, UsuarioRepositoryQueryEmail {
		 
	

	public List<Usuario> findByPermissoesDescricao(String permissaoDescricao);

}
