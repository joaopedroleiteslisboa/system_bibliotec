package com.system.bibliotec.repository;

import com.system.bibliotec.model.Reservas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Interface de Repositorio das reservas
 *
 * @author João Pedro
 * @see .. sql file > jpa-named-queries.properties
 * @since 10.09.2020
 */
@Repository
public interface ReservaRepository  extends JpaRepository<Reservas, Long>, GenericRepository<Reservas, Long>, JpaSpecificationExecutor<Reservas> {

    boolean existsById(Long id);

    @Query(nativeQuery = true)
    Optional<Reservas> findById(Long idReserva);

    void deleteById(Long id);

    @Query(nativeQuery = true)
    int isReservadoEAtiva(String statusReserva, Long idLivro, Long idUsuario);

    @Query(nativeQuery = true)
    int quantidadeReservaAtivaDoUsuario(String statusReserva, Long idUsuario);

}
