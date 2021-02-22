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
@Table(name = "systemErrors")
@EntityListeners(AuditingEntityListener.class)
public class SystemError {


    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "tb_excecao_sistema_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "tb_excecao_sistema_seq", sequenceName = "tb_excecao_sistema_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "id_excecao")
    private Long id;


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