package com.system.bibliotec.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.system.bibliotec.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	//@Query(value = "select u from Usuario u where u.email =:email")
	public Optional<Usuario> findUsuarioByEmail(String email);

	public List<Usuario> findByPermissoesDescricao(String permissaoDescricao);

}
