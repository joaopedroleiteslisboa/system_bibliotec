package com.system.bibliotec.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "funcionarios")
public class Funcionario extends AbstractAuditingEntity {


    /**
     *
     */
    private static final long serialVersionUID = 2281797851333807897L;


    @Column(name = "matricula", unique = true, nullable = false)
    private String matricula;


    @JoinColumn(name = "cargo")
    @OneToOne
    private Cargo cargo;

    @Column(name = "ativo", nullable = false)
    private boolean ativo;


}
