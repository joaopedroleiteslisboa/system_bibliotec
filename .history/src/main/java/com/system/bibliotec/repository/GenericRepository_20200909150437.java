package com.system.bibliotec.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.system.bibliotec.model.AbstractAuditingEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface GenericRepository<T extends AbstractAuditingEntity, ID extends Long>
extends PagingAndSortingRepository<T,ID> {



	@Query(value = "select count(*) from #{#entityName} where status='ATIVA' and idLivro=?1 and created_by =?#{principal}", nativeQuery = true)
	int isAtivaToUserContextAndlivro(Long idLivro);

	@Query(value = "select count(*) from #{#entityName} where status='ATIVA' and idLivro=?1 and idUsuario =?2", nativeQuery = true)
	int isAtivaToUserContextAndlivro(Long idLivro, Long idUsuario);
	
    @Query(value = "select count(*) from #{#entityName} where status=?1 and created_by =?#{principal}", nativeQuery = true)
	int quantidadeStatusDoUsuario(String status);

	@Query(value = "select count(*) from #{#entityName} where status=?1 and idUsuario =?2", nativeQuery = true)
	int quantidadeStatusDoUsuario(String status, long idUsuario);

	@Query("select e from #{#entityName} e where e.id =?1 and e.status = 'ATIVA' and e.usuario.email=?#{principal}")
	Optional<T> findOneGenericObjectAtivoToUser(Long id);

	@Query("select e from #{#entityName} e where e.id =?1 and e.status = 'ATIVA' and e.usuario.id=?2")
	Optional<T> findOneGenericObjectAtivoToUser(Long id, long idUsuario);
	
	@Query("select e from #{#entityName} e where e.livro.id =?1 and e.status = 'ATIVA' and e.usuario.email=?#{principal}")
	Optional<T> findOneGenericObjectAtivoToUserAndLivro(Long idLivro);

	@Query("select e from #{#entityName} e where e.livro.id =?1 and e.status = 'ATIVA' and e.usuario.id=?2")
	Optional<T> findOneGenericObjectAtivoToUserAndLivro(Long idLivro, long idUsuario);
	
	@Query("select e from #{#entityName} e where e.id =?1 and e.status = 'ATIVA' and e.usuario.email=?#{principal}")
	Optional<T> findOneGenericObjectToUser(Long id);

	@Query("select e from #{#entityName} e where e.id =?1 and e.status = 'ATIVA' and e.usuario.id=?2")
	Optional<T> findOneGenericObjectToUser(Long id, long idUsuario);
	
	@Query("select e from #{#entityName} e where e.status = 'ATIVA' and e.usuario.email=?#{principal}")
	Optional<List<T>> findAllGenericObjectAtivoToUser(Pageable pageable);

	@Query("select e from #{#entityName} e where e.status = 'ATIVA' and e.usuario.id=?1")
	Optional<List<T>> findAllGenericObjectAtivoToUser(long idUsuario, Pageable pageable);
	
	@Query("select e from #{#entityName} e where e.status = 'ATIVA'")
	Optional<List<T>> findAllGenericObjectAtivo();
	
	@Query("select e from #{#entityName} e where e.status = 'ATIVA'")
	Optional<List<T>> findAllGenericObjectAtivo(Pageable pageable);
	
	@Query("select e from #{#entityName} e where e.usuario.email=?#{principal}")
	Optional<List<T>> findAllGenericObjectToUser(Pageable pageable);

	@Query("select e from #{#entityName} e where e.usuario.id=?1")
	Optional<List<T>> findAllGenericObjectToUser(long idUsuario, Pageable pageable);

	@Query("select e from #{#entityName} e where e.id =?1")
	Optional<T> findById(Long id);

	@Transactional
	@Modifying
	@Query("update #{#entityName} e set e.status='CANCELADA' where e.id=?1 and e.usuario.email = ?#{principal}")
	void delete(Long id);

	@Transactional
	@Modifying
	@Query("update #{#entityName} e set e.status='CANCELADA' where e.id=?1 and e.usuario.id = ?2")
	void delete(Long id, long idUsuario);

	@Override
	@Transactional
	@Modifying
	default void delete(T t){
		delete(t.getId());
	}
}

