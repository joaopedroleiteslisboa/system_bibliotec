package com.system.bibliotec.model;

import javax.annotation.Generated;
import javax.persistence.*;

import com.system.bibliotec.model.enums.TipoErrorSistema;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * SystemError
 */


@Data
@Builder
@Entity
@Table(name = "TB_EXCECAO_SISTEMA")
@EntityListeners(AuditingEntityListener.class)
public class SystemError {


    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;


    @Column(name = "resolvido")
    private Boolean resolvido;

    @Column(name = "metodo_error")
    private String metodoError;

    @Column(name = "usuario_error")
    private String usuarioError;

    @Column(name = "id_user")
    private String idUser;

    @Column(name = "classs")
    private String classs;

    @Column(name = "ds_descricao")
    private String descricao;

    @Column(name = "ds_operacao")
    private String operacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_excecao")
    private TipoErrorSistema tipo;

}